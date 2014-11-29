package com.foxinmy.weixin4j.msg.event.menu;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 扫码推事件(scancode_push|scancode_waitmsg)
 * 
 * @className MenuScanEventMessage
 * @author jy
 * @date 2014年9月30日
 * @since JDK 1.7
 * @see <a
 *      href="http://mp.weixin.qq.com/wiki/index.php?title=%E8%87%AA%E5%AE%9A%E4%B9%89%E8%8F%9C%E5%8D%95%E4%BA%8B%E4%BB%B6%E6%8E%A8%E9%80%81#scancode_push.EF.BC.9A.E6.89.AB.E7.A0.81.E6.8E.A8.E4.BA.8B.E4.BB.B6.E7.9A.84.E4.BA.8B.E4.BB.B6.E6.8E.A8.E9.80.81">订阅号、服务号的扫码推事件</a>
 * @see <a
 *      href="http://qydev.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E4%BA%8B%E4%BB%B6#.E6.89.AB.E7.A0.81.E6.8E.A8.E4.BA.8B.E4.BB.B6.E7.9A.84.E4.BA.8B.E4.BB.B6.E6.8E.A8.E9.80.81">企业号的的扫码推事件</a>
 */
public class MenuScanEventMessage extends MenuEventMessage {

	private static final long serialVersionUID = 3142350663022709730L;

	@XStreamAlias("ScanCodeInfo")
	private ScanInfo scanInfo;

	public ScanInfo getScanInfo() {
		return scanInfo;
	}

	public static class ScanInfo {
		@XStreamAlias("ScanType")
		private String type;
		@XStreamAlias("ScanResult")
		private String result;

		public String getType() {
			return type;
		}

		public String getResult() {
			return result;
		}

		@Override
		public String toString() {
			return "ScanInfo [type=" + type + ", result=" + result + "]";
		}
	}

	@Override
	public String toString() {
		return "MenuScanEventMessage [scanInfo=" + scanInfo + ", "
				+ super.toString() + "]";
	}
}