package main.java.cn.com.sinosoft.app.pdf;

import java.util.Map;
/**
 * ģ������Ҫ�������ͼ
 * @ClassName: DocumentVo
 * @Description:ģ������Ҫ�������ͼ
 * @author lihengjun
 * �޸�ʱ�䣺 2013��11��5�� ����3:21:54
 * �޸����ݣ��½�
 */
public interface DocumentVo {
	/**
	 * ��ȡ����,���ڼ�¼��־
	 * @Title: findPrimaryKey
	 * @Description: ��ȡ����,���ڼ�¼��־
	 * @return
	 * @author lihengjun
	 * �޸�ʱ�䣺 2013��11��5�� ����3:00:10
	 * �޸����ݣ��½�
	 */
	public String findPrimaryKey();
	/**
	 * ��ȡ���map
	 * @Title: fillDataMap
	 * @Description: 
	 * @return
	 * @author lihengjun
	 * �޸�ʱ�䣺 2013��11��5�� ����11:19:29
	 * �޸����ݣ��½�
	 */
	public Map<String, Object> fillDataMap();
}
