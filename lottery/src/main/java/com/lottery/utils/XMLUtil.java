package com.lottery.utils;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 读取XML文件
 * @author Administrator
 */
public class XMLUtil {
	
	/**
	 * 获取蓝色球的个数
	 * @param typename
	 * @return
	 */
	public static int getTypeNum(String typename) {
		SAXReader reader = new SAXReader();
		URL url = XMLUtil.class.getClassLoader().getResource("lotteryType.xml");
		File file = new File(url.getPath());
		try {
			Document document = reader.read(file);
			Element ele = document.getRootElement();
			List<Element> nodes = ele.elements();
			for (Element element : nodes) {
				if (element.elementTextTrim("name").equals(typename)) {
					return TypeConversion.StrToInt(element.elementTextTrim("num"));
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	return 0;
	}
	
	/**
	 * 获取每个奖项的单注金额
	 * @param typename
	 * @return
	 */
	public static Map<String, BigDecimal> getMoney(String typename) {
		SAXReader reader = new SAXReader();
		URL url = XMLUtil.class.getClassLoader().getResource("lotteryType.xml");
		File file = new File(url.getPath());
		try {
			Document document = reader.read(file);
			Element ele = document.getRootElement();
			List<Element> nodes = ele.elements();
			for (Element element : nodes) {
				if (element.elementTextTrim("name").equals(typename)) {
					Map<String, BigDecimal> moneyMap = new HashMap<String, BigDecimal>();
					Element ele2 = element.element("prizes");
					List<Element> nodes2 = ele2.elements();
					for (Element element2 : nodes2) {
						String prizeName = element2.elementTextTrim("prizename");
						BigDecimal money = new BigDecimal(element2.elementTextTrim("money"));
						moneyMap.put(prizeName, money);
					}
					return moneyMap;
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	return null;
	}
	
	/**
	 * 得到对应彩种的中奖规则
	 * @param typename
	 * @return
	 */
	public static Map<String, Map<Integer, Integer>> getRule(String typename) {
		SAXReader reader = new SAXReader();
		URL url = XMLUtil.class.getClassLoader().getResource("lotteryType.xml");
		File file = new File(url.getPath());
		try {
			Document document = reader.read(file);
			Element ele = document.getRootElement();
			List<Element> nodes = ele.elements();
			for (Element element : nodes) {
				if (element.elementTextTrim("name").equals(typename)) {
					Element ele2 = element.element("prizes");
					List<Element> nodes2 = ele2.elements();
					Map<String, Map<Integer, Integer>> prizeMap = new HashMap<String, Map<Integer,Integer>>();
					for (Element element2 : nodes2) {
						Map<Integer, Integer> ballMap = new HashMap<Integer, Integer>();
						String prizeName = element2.elementTextTrim("prizename");
						String[] redBalls = element2.elementTextTrim("red").split(",");
						String[] blueBalls = element2.elementTextTrim("blue").split(",");
						for (int i=0; i<redBalls.length; i++) {
							ballMap.put(TypeConversion.StrToInt(redBalls[i]), TypeConversion.StrToInt(blueBalls[i]));
						}
						prizeMap.put(prizeName, ballMap);
					}
					return prizeMap;
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
}
