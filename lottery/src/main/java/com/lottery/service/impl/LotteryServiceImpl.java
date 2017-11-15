package com.lottery.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lottery.common.Const;
import com.lottery.common.ServerResponse;
import com.lottery.dao.BuyModelMapper;
import com.lottery.dao.DealModelMapper;
import com.lottery.dao.DrawDetailsModelMapper;
import com.lottery.dao.LotteryModelMapper;
import com.lottery.dao.TypeModelMapper;
import com.lottery.dao.UserModelMapper;
import com.lottery.pojo.BuyModel;
import com.lottery.pojo.DealModel;
import com.lottery.pojo.DrawDetailsModel;
import com.lottery.pojo.LotteryModel;
import com.lottery.pojo.TypeModel;
import com.lottery.pojo.UserModel;
import com.lottery.service.ILotteryService;
import com.lottery.utils.TypeConversion;
import com.lottery.utils.XMLUtil;
import com.lottery.vo.LotteryVo;
@Service
public class LotteryServiceImpl implements ILotteryService {
	@Autowired
	private LotteryModelMapper lotterdao;
	@Autowired
	private TypeModelMapper typedao;
	@Autowired
	private BuyModelMapper buydao;
	@Autowired
	private DrawDetailsModelMapper detailsdao;
	@Autowired
	private DealModelMapper dealdao;
	@Autowired
	private UserModelMapper userdao;

	@Override
	public int deleteByPrimaryKey(Short lotterid) {
		return lotterdao.deleteByPrimaryKey(lotterid);
	}

	@Override
	public int insert(LotteryModel record) {
		return lotterdao.insert(record);
	}

	@Override
	public int insertSelective(LotteryModel record) {
		return lotterdao.insertSelective(record);
	}

	@Override
	public LotteryModel selectByPrimaryKey(Short lotterid) {
		return lotterdao.selectByPrimaryKey(lotterid);
	}

	@Override
	public int updateByPrimaryKeySelective(LotteryModel record) {
		return lotterdao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LotteryModel record) {
		return lotterdao.updateByPrimaryKey(record);
	}

	@Override
	public PageInfo<LotteryVo> queryAll(LotteryModel lottory, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<LotteryVo> lotteryList = lotterdao.queryAll(lottory);
		PageInfo<LotteryVo> pageResult = new PageInfo<LotteryVo>(lotteryList);
		List<LotteryVo> lotteryListNew = new ArrayList<LotteryVo>();
		Timestamp nowTime = new Timestamp(new Date().getTime());
		for (LotteryVo lotteryVo : lotteryList) {
			if (nowTime.before(lotteryVo.getStarttime())) {   //如果不到开奖时间就设开奖号码为0
				lotteryVo.setDrawno("0");
			}
			lotteryListNew.add(lotteryVo);
		}
		pageResult.setList(lotteryList);
		return pageResult;
	}

	/**
	 * 自动生成彩票
	 */
	@Override
	@Transactional
	public void generate() {   //TODO:线程同步问题
		List<TypeModel> typeList = typedao.queryAll(null);
		for (TypeModel type : typeList) {
			LotteryModel lotter = new LotteryModel();
			lotter.setLid(type.getLid());
			List<LotteryVo> lotterList = lotterdao.queryAll(lotter); //查询是否有此类彩票
			String[] drawdays = type.getLotterdate().split("、");
			String[] time = type.getLottertime().split(":");
			lotter.setStarttime(getStartTime(drawdays, time));   //设置开奖时间
			lotter.setDrawno(generateNo());   //设置中奖号码
			lotter.setDrawnum(BigDecimal.valueOf(0));  //设置中奖人数
			lotter.setTotalsale(BigDecimal.valueOf(0));//设置销售总价
			lotter.setDrawmoney(BigDecimal.valueOf(0));//设置中奖金额
			//没有此类别的彩票，但有此彩票类别，添加第一期彩票
			if (lotterList.isEmpty()) {
				Calendar c = Calendar.getInstance();
				lotter.setIssue(String.valueOf(c.get(Calendar.YEAR)).substring(2)+"001");
				lotter.setAccumulated(Const.INIT_MONEY);
				int insertCount = lotterdao.insertSelective(lotter);
				//添加记录
				if (insertCount > 0) {
					System.out.println("第"+lotter.getIssue()+"期"+type.getLotypename()+"彩票生成成功！");
					detailsAdd(type.getLid(), lotter.getIssue(), type.getLotypename());
				}
			} else {  //有此类彩票
				//通过得到最新一期的期号，设置当前期号
				String issueOld = lotterdao.selectIssue(type.getLid());
				String starttimeOld = lotterdao.selectStartTimeByIssueAndLid(type.getLid(), issueOld);
				Date d = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("此刻时间：---------------"+sf.format(d));
				
//				isMate(type.getLid(), type.getLotypename(), issueOld, "11121314151617");
				
				//判断此时是不是上一期的开奖时间，如果是进行核对并且添加下一期彩票
				if (starttimeOld!=null && starttimeOld.equals(sf.format(d))) {
					System.out.println("+++++++++++++++++++++++++开奖");
					LotteryModel lo = new LotteryModel();
					lo.setLid(type.getLid());
					lo.setStatus((short) 1);
					lo.setIssue(issueOld);
					lotterdao.updateByLidAndIssue(lo);  //修改彩票状态
//					lo.setStatus((short) 0);
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+lo);
					List<LotteryVo> loList = lotterdao.queryAll(lo);
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+loList);
					String drawNo = loList.isEmpty()?"":loList.get(0).getDrawno();   //得到上一期的中奖号码
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+drawNo);
					isMate(type.getLid(), type.getLotypename(), issueOld, drawNo);  //进行奖项的匹配
					
					//添加下一期
					if (type.getStatus() == 0) {   //判断此类型彩票是否还要运行
						System.out.println("-----------------------");
						System.out.println("-----------------------");
						System.out.println("-----------------------");
						System.out.println("-----------------------");
						System.out.println("-----------------------");
						System.out.println("-----------------------");
						System.out.println("-----------------------");
						String issue = String.valueOf(TypeConversion.StrToInt(issueOld)+1);
						//获取上一期的滚存并加上初始化奖池的钱添加为本期滚存
						BigDecimal accumulated = lotterdao.selectByLidAndIssue(type.getLid(), issueOld).getAccumulated();
						lotter.setIssue(issue);
						lotter.setAccumulated(accumulated.add(Const.INIT_MONEY));
						System.out.println("-------------------"+type.getLid()+","+issue+","+accumulated);
						//添加记录
						int insertCount = lotterdao.insertSelective(lotter);
						//向详情表中添加记录
						if (insertCount > 0) {
							System.out.println("第"+lotter.getIssue()+"期"+type.getLotypename()+"彩票生成成功！");
							detailsAdd(type.getLid(), lotter.getIssue(), type.getLotypename());
						}
					}
				}
			}
		}
	}

	/**
	 * 生成彩票时向详情表中添加记录
	 * @param lid
	 * @param issue
	 * @param typename
	 */
	@Transactional
	private void detailsAdd(short lid, String issue, String typename) {
		LotteryModel newLottery = lotterdao.selectByLidAndIssue(lid, issue);
		DrawDetailsModel detail = new DrawDetailsModel();
		detail.setLotterid(newLottery.getLotterid());
		detail.setDrawnum(BigDecimal.valueOf(0));
		//从配置文件中读取
		Map<String, BigDecimal> moneyMap = XMLUtil.getMoney(typename);
		if (moneyMap != null) {
			Set<String> keySet = moneyMap.keySet();
			Iterator<String> keyIt = keySet.iterator();
			while (keyIt.hasNext()) {
				String prize = keyIt.next();
				BigDecimal money = moneyMap.get(prize);
				detail.setPrize(prize);
				detail.setDrawmoney(money);
				int addCount = detailsdao.insertSelective(detail);  //添加信息
				if (addCount > 0) {
					System.out.println("添加成功");
				} else {
					System.out.println("添加失败");
				}
			}
		}
	}
	
	/**
	 * 随机生成中奖号码
	 * @return
	 */
	private String generateNo() {
		List<Integer> list = new ArrayList<Integer>(7);
		Random random = new Random();
		for (int i=0; i<6; i++) {
			boolean flag = true;
			int n = random.nextInt(35)+1;
			if (i == 0) {
				list.add(n);
				continue;
			}
			//判断是否重复
			for (int j=0; j<list.size(); j++) {
				if (n == list.get(j)) {
					i--;
					flag = false;
					break;
				}
			}
			if (flag) {
				list.add(n);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Integer no : list) {
			if (no < 10) {   //如果是个位数就在前面补0
				sb.append("0"+no);
			} else {
				sb.append(no);
			}
		}
		int late = random.nextInt(12)+1;   //蓝球
		if (late < 10) {   //如果是个位数就在前面补0
			sb.append("0"+late);
		} else {
			sb.append(late);
		}
		return sb.toString();
	}
	
	/**
	 * 计算开奖日期
	 * @param drawdays
	 * @param time
	 * @return
	 */
	private Timestamp getStartTime(String[] drawdays, String[] time) {
		Calendar c = Calendar.getInstance();
		String today = "";
		
		c.set(Calendar.HOUR_OF_DAY, TypeConversion.StrToInt(time[0]));
		c.set(Calendar.MINUTE, TypeConversion.StrToInt(time[1]));
		c.set(Calendar.SECOND, 0);
		
		//得到今天是星期几
		for (int i=0; i<7; i++) {
			if (c.get(Calendar.DAY_OF_WEEK)==(i+1)) {
				today = String.valueOf(Const.weekday.values()[i]);
				break;
			}
		}
//		System.out.println("---------------今天星期"+today);
		
		for (String drawday : drawdays) {
			//如果开奖日中有今天
			if (drawday.equals(today)) {
				 return new Timestamp(c.getTimeInMillis());
			}
		}
		
		//如果开奖日没有今天，寻找最近的开奖日
		List<Integer> timeLagList = new ArrayList<Integer>();
		for (String drawday : drawdays) {
			for (int i=0; i<7; i++) {
				//得到开奖日，计算与当前日期的时间差，并存入一个list集合
				if (drawday.equals(String.valueOf(Const.weekday.values()[i]))) {
//					System.out.println("---------------------"+drawday+","+String.valueOf(Const.weekday.values()[i]));
					if (i < (c.get(Calendar.DAY_OF_WEEK)-1)) {  //当开奖日在当前日期之前
//						System.out.println("-----------------"+i+"-"+c.get(Calendar.DAY_OF_WEEK)+"-1+7="+(i-(c.get(Calendar.DAY_OF_WEEK)-1)+7));
						timeLagList.add(i-(c.get(Calendar.DAY_OF_WEEK)-1)+7);
					} else {
//						System.out.println("-----------------"+i+"-"+c.get(Calendar.DAY_OF_WEEK)+"-1="+(i-(c.get(Calendar.DAY_OF_WEEK)-1)));
						timeLagList.add(i-(c.get(Calendar.DAY_OF_WEEK)-1));
					}
				}
			}
		}
//		System.out.println("-----------------"+Collections.min(timeLagList));
		c.add(Calendar.DAY_OF_YEAR, +Collections.min(timeLagList));  //找出集合中时间差最短的进行设置
		return new Timestamp(c.getTimeInMillis());
	}
	
	/**
	 * 某类彩票的匹配得奖情况
	 * @param lid
	 * @param issue
	 * @param drawNo
	 */
	@Transactional      //添加事务
	private void isMate(short lid, String typename, String issue, String drawNo) {
		System.out.println("++++++++++++++++++++++++判断开奖情况");
		System.out.println("**********************"+lid+","+typename+","+issue);
		List<BuyModel> buyList = buydao.selectByLottery(lid, issue);  //获取买此期彩票的用户列表以及此期彩票id
		LotteryModel lm = lotterdao.selectByLidAndIssue(lid, issue);  
		BigDecimal accumulated = lm.getAccumulated(); //得到此期彩票的滚存
		BigDecimal accumulatedCopy = accumulated;   //在一等奖和二等奖中使用，防止前面修改滚存而影响了后面
		BigDecimal totalsale = lm.getTotalsale(); //得到此期彩票的销售总额
		List<DrawDetailsModel> details = detailsdao.selectDetailByLidAndIssue(lid, issue); //得到此期彩票的奖项和此奖项的奖金
		BigDecimal first = new BigDecimal(0);
		BigDecimal second = new BigDecimal(0);
		BigDecimal third = new BigDecimal(0);
		BigDecimal fourth = new BigDecimal(0);
		BigDecimal fifth = new BigDecimal(0);
		BigDecimal sixth = new BigDecimal(0);
		//获取奖项对应的奖金
		for (DrawDetailsModel detail : details) {
			switch (detail.getPrize()) {
			case "一等奖":
				first = detail.getDrawmoney();
				System.out.println("-------------"+first);
				break;
			case "二等奖":
				second = detail.getDrawmoney();
				System.out.println("-------------"+second);
				break;
			case "三等奖":
				third = detail.getDrawmoney();
				System.out.println("-------------"+third);
				break;
			case "四等奖":
				fourth = detail.getDrawmoney();
				System.out.println("-------------"+fourth);
				break;
			case "五等奖":
				fifth = detail.getDrawmoney();
				System.out.println("-------------"+fifth);
				break;
			case "六等奖":
				sixth = detail.getDrawmoney();
				System.out.println("-------------"+sixth);
				break;
			default:
				break;
			}
		}
		System.out.println("**********************"+accumulated+",");
		
		        //TODO:判断滚存小于固定奖奖金的情况
		BigDecimal firstNum = new BigDecimal(0);    //一等奖中奖注数
		BigDecimal secondNum = new BigDecimal(0);  //二等奖中奖注数
		BigDecimal thirdNum = new BigDecimal(0);    //三等奖中奖注数
		BigDecimal fourNum = new BigDecimal(0);    //四等奖中奖注数
		BigDecimal fifNum = new BigDecimal(0);    //五等奖中奖注数
		BigDecimal sixNum = new BigDecimal(0);    //六等奖中奖注数
		
		if (!buyList.isEmpty()) {
			LotteryModel lottery = new LotteryModel();
			lottery.setIssue(issue);
			lottery.setLid(lid);
			List<Short> firstUserIdList = new ArrayList<Short>();   //保存获得一等奖的用户的userid
			List<Short> secondUserIdList = new ArrayList<Short>();   //保存获得二等奖的用户的userid
			
			for (BuyModel buy : buyList) {
				short userid = buy.getUserid();  //得到买此期彩票的用户id
				String buyNo = buy.getBuyno();   //得到此用户买的号
				UserModel u = userdao.selectByPrimaryKey(userid);   //得到此用户信息
				UserModel user = new UserModel();
				user.setUserid(userid);
				DealModel deal = new DealModel();
				deal.setUserid(userid);
				DrawDetailsModel d = null;
				//一等奖情况                        
				if (isDraw(buyNo, drawNo, typename, "一等奖")) {
					firstNum = firstNum.add(BigDecimal.valueOf(1));   //注数加一
					firstUserIdList.add(userid);  //保存用户id
					d = detailsdao.selectByLotteridAndPrize(buyList.get(0).getLotterid(), "一等奖");
					//更新中奖详情表
					d.setDrawnum(d.getDrawnum().add(BigDecimal.valueOf(1)));   //中奖注数加一
					detailsdao.updateByPrimaryKeySelective(d);   //并且修改数据库
					buydao.updateIsDrawByBuyNo(buyNo, "一等奖");   //修改购买记录中是否中奖
					continue;
				//二等奖
				} else if (isDraw(buyNo, drawNo, typename, "二等奖")) {
					secondNum = secondNum.add(BigDecimal.valueOf(1));
					secondUserIdList.add(userid);  //保存用户id
					d = detailsdao.selectByLotteridAndPrize(buyList.get(0).getLotterid(), "二等奖");
					//更新中奖详情表
					d.setDrawnum(d.getDrawnum().add(BigDecimal.valueOf(1)));   //中奖注数加一
					detailsdao.updateByPrimaryKeySelective(d);   //并且修改数据库
					buydao.updateIsDrawByBuyNo(buyNo, "二等奖");
					continue;
				//三等奖
				} else if (isDraw(buyNo, drawNo, typename, "三等奖")) {
					thirdNum = thirdNum.add(BigDecimal.valueOf(1));
					user.setBalance(u.getBalance().add(third));  //设置用户余额
					deal.setDealmoney(third);   //设置交易金额
					deal.setDealinfo("中"+typename+"彩票三等奖");  //设置交易信息
					d = detailsdao.selectByLotteridAndPrize(buy.getLotterid(), "三等奖");
					accumulated = accumulated.subtract(third);  //设置此类彩票的滚存
					buydao.updateIsDrawByBuyNo(buyNo, "三等奖");
				//四等奖
				} else if (isDraw(buyNo, drawNo, typename, "四等奖")) {
					fourNum = fourNum.add(BigDecimal.valueOf(1));
					user.setBalance(u.getBalance().add(fourth));  //设置用户余额
					deal.setDealmoney(fourth);   //设置交易金额
					deal.setDealinfo("中"+typename+"彩票四等奖");  //设置交易信息
					d = detailsdao.selectByLotteridAndPrize(buy.getLotterid(), "四等奖");
					accumulated = accumulated.subtract(fourth);  //设置此类彩票的滚存
					buydao.updateIsDrawByBuyNo(buyNo, "四等奖");
				//五等奖
				} else if (isDraw(buyNo, drawNo, typename, "五等奖")) {
					fifNum = fifNum.add(BigDecimal.valueOf(1));
					user.setBalance(u.getBalance().add(fifth));  //设置用户余额
					deal.setDealmoney(fifth);   //设置交易金额
					deal.setDealinfo("中"+typename+"彩票五等奖");  //设置交易信息
					d = detailsdao.selectByLotteridAndPrize(buy.getLotterid(), "五等奖");
					accumulated = accumulated.subtract(fifth);  //设置此类彩票的滚存
					buydao.updateIsDrawByBuyNo(buyNo, "五等奖");
				//六等奖
				} else if (isDraw(buyNo, drawNo, typename, "六等奖")) {
					sixNum = sixNum.add(BigDecimal.valueOf(1));
					user.setBalance(u.getBalance().add(sixth));  //设置用户余额
					deal.setDealmoney(sixth);   //设置交易金额
					deal.setDealinfo("中"+typename+"彩票六等奖");  //设置交易信息
					d = detailsdao.selectByLotteridAndPrize(buy.getLotterid(), "六等奖");
					accumulated = accumulated.subtract(sixth);  //设置此类彩票的滚存
					buydao.updateIsDrawByBuyNo(buyNo, "六等奖");
				} else {
					System.out.println("-----------------没获奖");
					buydao.updateIsDrawByBuyNo(buyNo, "没获奖");
					continue;   //此类彩票无中奖情况，跳出此次循环
				}
				System.out.println("+++++++++++++++++++++++++++++++");
				//更新用户余额
				userdao.updateByPrimaryKeySelective(user);
				//添加一条交易信息
				deal.setDealdate(new Timestamp(new Date().getTime()));   //设置交易时间
				dealdao.insertSelective(deal);   //添加交易信息
				//更新中奖详情表
				d.setDrawnum(d.getDrawnum().add(BigDecimal.valueOf(1)));   //中奖注数加一
				detailsdao.updateByPrimaryKeySelective(d);   //并且修改数据库
			}
			//计算单注一等奖和但单注二等奖奖金    ((销售总额+滚存)-总固定奖金)*(70%或30%)/(奖项对应注数)
			BigDecimal fixed = third.multiply(thirdNum).add(fourth.multiply(fourNum)).add(fifth.multiply(fifNum)).add(sixth.multiply(sixNum));
			BigDecimal firstMoney = new BigDecimal(0);
			BigDecimal secondMoney = new BigDecimal(0);
			if (firstNum.compareTo(BigDecimal.valueOf(0)) != 0) {   //判断注数是否为0
				firstMoney = totalsale.add(accumulatedCopy).subtract(fixed).multiply(first).divide(firstNum);
			}
			if (firstMoney.compareTo(BigDecimal.valueOf(100000)) > 0) {  //单注奖金不得超过10W
				firstMoney = BigDecimal.valueOf(100000);
			}
			if (secondNum.compareTo(BigDecimal.valueOf(0)) != 0) {
				secondMoney = totalsale.add(accumulatedCopy).subtract(fixed).multiply(second).divide(secondNum);
			}
			if (secondMoney.compareTo(BigDecimal.valueOf(50000)) > 0) {  //单注奖金不得超过5W
				secondMoney = BigDecimal.valueOf(50000);
			}
			
			System.out.println("**&&&&&&&&&***************&&&&&&&&&&&*************"+accumulatedCopy+","+totalsale+","+fixed+","+firstMoney+","+secondMoney);
			//对一等奖用户进行操作
			for (Short firstUser : firstUserIdList) {
				UserModel u = userdao.selectByPrimaryKey(firstUser);   //得到此用户信息
				DealModel deal = new DealModel();
				UserModel user = new UserModel();
				user.setUserid(firstUser);
				deal.setDealmoney(firstMoney);   //设置此用户的交易金额
				user.setBalance(u.getBalance().add(firstMoney));  //设置用户余额
				deal.setDealinfo("中"+typename+"彩票一等奖");  //设置交易信息
				deal.setUserid(firstUser);  //设置用户id
				deal.setDealdate(new Timestamp(new Date().getTime()));   //设置交易时间
				dealdao.insertSelective(deal);   //添加交易信息
				userdao.updateByPrimaryKeySelective(user);  //更新用户余额
				accumulated = accumulated.subtract(firstMoney);  //对滚存进行减操作
			}
			//对二等奖用户进行操作
			for (Short secondUser : secondUserIdList) {
				UserModel u = userdao.selectByPrimaryKey(secondUser);   //得到此用户信息
				UserModel user = new UserModel();
				user.setUserid(secondUser);
				DealModel deal = new DealModel();
				deal.setDealmoney(secondMoney);   //设置此用户的交易金额
				user.setBalance(u.getBalance().add(secondMoney));  //设置用户余额
				deal.setDealinfo("中"+typename+"彩票二等奖");  //设置交易信息
				deal.setUserid(secondUser);  //设置用户id
				deal.setDealdate(new Timestamp(new Date().getTime()));   //设置交易时间
				dealdao.insertSelective(deal);   //添加交易信息
				userdao.updateByPrimaryKeySelective(user);  //更新用户余额
				accumulated = accumulated.subtract(secondMoney);  //对滚存进行减操作
			}
			//更新此期彩票的滚存、中奖人数、中奖金额
			lottery.setAccumulated(accumulated);
			lottery.setDrawmoney(firstNum.multiply(firstMoney).add(secondNum.multiply(secondMoney))
					.add(thirdNum.multiply(third)).add(fourNum.multiply(fourNum))
					.add(fifNum.multiply(fifth).add(sixNum.multiply(sixth))));
			lottery.setDrawnum(firstNum.add(secondNum).add(thirdNum).add(fourNum).add(fifNum).add(sixNum));
			lotterdao.updateByLidAndIssue(lottery);
		}
	}
	
	/**
	 * 判断是否中奖
	 * @param buyNo
	 * @param drawNo
	 * @param typename
	 * @param prize
	 * @return
	 */
	private static boolean isDraw(String buyNo, String drawNo, String typename, String prize) {
		int blueBall = XMLUtil.getTypeNum(typename);  //读取蓝色球的个数
		int redEqualeCount = 0;  //红球相等的个数
		int blueEqualeCount = 0;  //蓝球相等的个数
		System.out.println("################################判断奖项"+drawNo+","+drawNo.length());
		if (blueBall != 0) {
			//判断红球相等个数
			for (int i=0; i<buyNo.length()-(blueBall*2); i+=2) {
				for (int j=0; j<drawNo.length()-(blueBall*2); j+=2) {
					if (buyNo.substring(i, i+2).equals(drawNo.substring(j, j+2))) {
						redEqualeCount++;
						continue;
					}
				}
			}
			//判断蓝球相等个数
			for (int i=(buyNo.length()-blueBall*2); i<buyNo.length()-1; i+=2) {
				System.out.println("@@@@@@@@@@@@@@@@@@@@"+blueBall+",i="+i);
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^"+buyNo.substring(i, i+2));
				for (int j=(drawNo.length()-blueBall*2); j<drawNo.length()-1; j+=2) {
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^   j="+j);
					System.out.println("^^^^^^^^^^^^^^^^^^^^^^^"+drawNo.substring(j, j+2));
					System.out.println("========================"+buyNo);
					System.out.println("========================"+buyNo);
					System.out.println("========================"+buyNo);
					System.out.println("========================"+buyNo);
					System.out.println("========================"+buyNo);
					if (buyNo.substring(i, i+2).equals(drawNo.substring(j, j+2))) {
						System.out.println("---->^^^^^^^^^^^^^^^^^^^^^^^"+drawNo.substring(j, j+2));
						blueEqualeCount++;
						continue;
					}
				}
			}
			System.out.println("--------------红球相等个数："+redEqualeCount+",蓝球相等个数："+blueEqualeCount);
			
			Map<String, Map<Integer, Integer>> ruleMap = XMLUtil.getRule(typename);  //获取中奖规则
			Map<Integer, Integer> ballMap = new HashMap<Integer, Integer>();
			if (ruleMap != null) {  //判断是否有此类彩票的开奖规则
				Set<String> keySet = ruleMap.keySet();
				Iterator<String> keyIt = keySet.iterator();
				while (keyIt.hasNext()) {
					String prizeName = keyIt.next();
					System.out.println("-----------------"+prizeName+","+prize);
					if (prizeName.equals(prize)) {    //只需要判断当前要查询的奖项即可
						ballMap = ruleMap.get(prizeName);   //得到存放红蓝球个数的集合
						Set<Integer> redBalls = ballMap.keySet();
						Iterator<Integer> redBallIt = redBalls.iterator();
						while (redBallIt.hasNext()) {
							int redBallNum = redBallIt.next();
							int blueBallNum = ballMap.get(redBallNum);
							System.out.println("------------------规则中：红球："+redBallNum+",蓝球："+blueBallNum);
							//判断红蓝球的相等个数是否与要判断奖项的规则相同
							if (redEqualeCount==redBallNum && blueEqualeCount==blueBallNum) {
								System.out.println("++++++++++++中"+prize);
								return true;
							}
						}
						break;
					}
				}
			}
		}
		return false;
	}

	@Override
	public List<LotteryModel> selectIssueByGroup() {
		return lotterdao.selectIssueByGroup();
	}

}
