package com.foxinmy.weixin4j.msg;

import java.io.Serializable;
import java.io.Writer;

import com.foxinmy.weixin4j.type.MessageType;
import com.foxinmy.weixin4j.util.WeixinConfig;
import com.foxinmy.weixin4j.util.WeixinUtil;
import com.foxinmy.weixin4j.xml.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

/**
 * 普通消息基类
 * <p><font color="red">回复图片等多媒体消息时需要预先上传多媒体文件到微信服务器,
 * 假如服务器无法保证在五秒内处理并回复，可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试</font></p>
 * @className BaseMessage
 * @author jy.hu
 * @date 2014年4月6日
 * @since JDK 1.7
 */
public class BaseMessage implements Serializable {

	private static final long serialVersionUID = 7761192742840031607L;

	@XStreamAlias("ToUserName")
	private String toUserName; // 开发者微信号
	@XStreamAlias("FromUserName")
	private String fromUserName; // 发送方帐号（一个OpenID）
	@XStreamAlias("CreateTime")
	private long createTime = System.currentTimeMillis(); // 消息创建时间 （整型）
	@XStreamAlias("MsgType")
	private MessageType msgType; // 消息类型
	@XStreamAlias("MsgId")
	private long msgId; // 消息ID

	public BaseMessage(MessageType msgType) {
		this.msgType = msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BaseMessage) {
			return ((BaseMessage) obj).getMsgId() == msgId;
		}
		return false;
	}
	
	/**
	 * 消息对象转换为微信服务器接受的xml格式消息
	 * @return xml字符串
	 */
	public String toXml() {
		XStream xstream = new XStream();
		xstream.alias("xml", getMsgType().getMessageClass());
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(getMsgType().getMessageClass());
		xstream.omitField(BaseMessage.class, "msgId");
		if (WeixinUtil.isBlank(fromUserName)) {
			setFromUserName(WeixinConfig.getValue("app_openId"));
		}
		return xstream.toXML(this);
	}

	/**
	 * 消息对象转换为微信服务器接受的json格式字符串
	 * @return json字符串
	 */
	public String toJson() {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			}
		});
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(getMsgType().getMessageClass());
		xstream.omitField(BaseMessage.class, "msgId");
		if (WeixinUtil.isBlank(fromUserName)) {
			setFromUserName(WeixinConfig.getValue("app_openId"));
		}
		return xstream.toXML(this);
	}
}