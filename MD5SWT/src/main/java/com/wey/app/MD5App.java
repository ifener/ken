package com.wey.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

public class MD5App {

	JTextField txtTimestamp;
	JTextField txtAppkey;
	JTextField txtVersion;
	JTextField txtJson;
	JTextField txtUrl;
	JTextPane jp;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MD5App().init();
	}
	
	public void init()
	{

		JFrame dialog = new JFrame();
		// 设置大小
		dialog.setSize(500, 600);
		// 设置标题
		dialog.setTitle("API调用");
		// 时间
		JLabel lblTimestamp = new JLabel("时间：");
		lblTimestamp.setBounds(10, 10, 80, 30);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		txtTimestamp = new JTextField();
		txtTimestamp.setBounds(90, 10, 390, 30);
		txtTimestamp.setText(format.format(new Date()));
		dialog.add(lblTimestamp);
		dialog.add(txtTimestamp);

		// APP KEY
		JLabel lblAppkey = new JLabel("APPKEY：");
		lblAppkey.setBounds(10, 50, 80, 30);
		txtAppkey = new JTextField();
		txtAppkey.setBounds(90, 50, 390, 30);
		txtAppkey.setText("a31b4ed1-52d4-4272-ae6b-e3164b18a207");
		dialog.add(lblAppkey);
		dialog.add(txtAppkey);

		// VERSION
		JLabel lblVersion = new JLabel("版本：");
		lblVersion.setBounds(10, 90, 80, 30);
	    txtVersion = new JTextField();
		txtVersion.setBounds(90, 90, 390, 30);
		txtVersion.setText("1.0");
		dialog.add(lblVersion);
		dialog.add(txtVersion);

		// JSON参数
		JLabel lblJson = new JLabel("JSON参数：");
		lblJson.setBounds(10, 130, 80, 30);
		txtJson = new JTextField();
		txtJson.setBounds(90, 130, 390, 30);
		dialog.add(lblJson);
		dialog.add(txtJson);
		
		// 请求地址
		JLabel lblUrl = new JLabel("请求URL：");
		lblUrl.setBounds(10, 170, 80, 30);
		txtUrl = new JTextField();
		txtUrl.setBounds(90, 170, 390, 30);
		dialog.add(lblUrl);
		dialog.add(txtUrl);


		JButton btnSend = new JButton("发送");
		btnSend.setBounds(200, 210, 60, 40);
		dialog.add(btnSend);

		btnSend.addActionListener(new ActionListener() { // 为确定按钮添加监听事件

			public void actionPerformed(ActionEvent arg0) {
				
				String appKey = txtAppkey.getText().trim();
				if(appKey.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "请输入APPKEY");
					return;
				}
				
				String url = txtUrl.getText();
				if(url.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "请输入URL");
					return;
				}
				
				String json = txtJson.getText();
				if(json.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "请输入JSON");
					return;
				}
				
				String timestamp = txtTimestamp.getText();
				if(timestamp.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "请输入时间");
					return;
				}
				
				StringBuilder builder = new StringBuilder("appkey");
				builder.append(appKey);
				builder.append("paramjson");
				builder.append(json);
				builder.append("timestamp");
				builder.append(timestamp);
				builder.append("v");
				builder.append(txtVersion.getText());
				String sign = MD5Util.encrypt(builder.toString());
				StringBuilder urlBuilder = new StringBuilder(url);
				urlBuilder.append("?");
				urlBuilder.append("v=");
				urlBuilder.append(txtVersion.getText());
				urlBuilder.append("&paramjson=");
				
				try {
					json = URLEncoder.encode(json, "UTF-8");
					timestamp = URLEncoder.encode(timestamp, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				urlBuilder.append(json);
				urlBuilder.append("&timestamp=");
				urlBuilder.append(timestamp);
				urlBuilder.append("&sign=");
				urlBuilder.append(sign);
				
				
				String responseText = HttpClientUtil.doGet(urlBuilder.toString());
				jp.setText(responseText);
			}
		});

		dialog.setLayout(null);

		jp = new JTextPane();
		jp.setBounds(10, 280, 470, 200);
		dialog.add(jp);

		// 设置dislog的相对位置，参数为null，即显示在屏幕中间
		dialog.setLocationRelativeTo(null);
		// 设置当用户在此对话框上启动 "close" 时默认执行的操作
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// 设置是否显示
		dialog.setVisible(true);
	
	}

}
