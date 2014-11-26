package findIt;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * FindIt 界面
 * @author SkyFire
 *
 */
class FindItGui extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 当前任务数量
	 */
	private int thNum=0;
	/**
	 * 关键字输入框
	 */
	private JTextArea inKey=new JTextArea(2,15);
	/**
	 * 表达式输入框
	 */
	private JTextArea inExpr=new JTextArea(2,80);
	/**
	 * 目录输入框
	 */
	private JTextField inDir=new JTextField(15);
	/**
	 * &按钮
	 */
	private JButton andBt=new JButton("&");
	/**
	 * |按钮
	 */
	private JButton orBt=new JButton("|");
	/**
	 * ^按钮
	 */
	private JButton notBt=new JButton("^");
	/**
	 * 浏览 按钮
	 */
	private JButton chooseBt=new JButton("浏览");
	/**
	 * 开始筛选按钮
	 */
	private JButton startBt=new JButton("开始筛选");
	/**
	 * 关于 按钮
	 */
	private JButton aboutBt=new JButton("关于");
	/**
	 * 关键字 提示标签
	 */
	private JLabel keyLb=new JLabel("关键字：");
	/**
	 * 表达式 提示标签
	 */
	private JLabel allLb=new JLabel("表达式：所有文件");
	/**
	 * 选择目录 提示标签
	 */
	private JLabel chooseLb=new JLabel("选择目录：");
	/**
	 * 线程数量显示标签
	 */
	private JLabel thNumLb=new JLabel();
	/**
	 * 日志显示
	 */
	private JTextArea msgOut=new JTextArea(null,null,4,50);
	/**
	 * 日志显示 滚动条
	 */
	private JScrollPane pane=new JScrollPane(msgOut);
	
	/**
	 * &、|、^按钮按下
	 * @author SkyFire
	 *
	 */
	class andListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!inKey.getText().isEmpty()){
				inExpr.setText(inExpr.getText()+((JButton)e.getSource()).getText()+"\""+inKey.getText()+"\"");
				inKey.setText("");
			}
		}
	}
	/**
	 * 浏览 按钮按下
	 * @author SkyFire
	 *
	 */
	class chooseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChoose=new JFileChooser();
			fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int value=fileChoose.showOpenDialog(FindItGui.this);
			if(value==JFileChooser.APPROVE_OPTION){
				inDir.setText(fileChoose.getSelectedFile().toString());
			}
		}
		
	}
	
	/**
	 * 开始筛选 按钮按下
	 * @author SkyFire
	 *
	 */
	class startListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(inExpr.getText().toString().isEmpty()||inDir.getText().toString().isEmpty()){
				errorOut(null,null,"请将信息填写完整！",0);
			}else{
				++thNum;
				refThNum();
				receMsg(inExpr.getText(),inDir.getText(),"启动");
				Thread t=new Thread(new startAna());
				t.start();
			}
		}
	}
	
	/**
	 * 关于按钮按下
	 * @author SkyFire
	 *
	 */
	class aboutListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame abt=new JFrame("关于");
			abt.setResizable(false);
			abt.setBounds(200,100,800,600);
			abt.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			JTextArea aboutTxt=new JTextArea();
			aboutTxt.setEditable(false);
			aboutTxt.setLineWrap(true);
			/*
			 * 关于 内容
			 */
			aboutTxt.setText("FindIt 说明文档\n\n" +
					"版本升级日志:\n" +
					"	2014-11-25:支持关键字里有换行符,优化GUI\n\n" +
					"一、功能描述\n" +
					"	筛选指定目录下满足指定条件的文档\n\n"+
					"二、特色\n" +
					"	1.多任务\n" +
					"		可同时进行多个筛选任务\n" +
					"	2.逻辑筛选\n" +
					"		可以将关键字通过‘&’、“|”、‘^’组合起来进行逻辑筛选，如查找同时含有“西安”、“大学”,但是不包括“外国语”的文档，则可以输入表达式：" +
					"&\"西安\"&\"大学\"^\"外国语\"\n" +
					"	3.支持多种文件格式筛选\n" +
					"		目前支持.txt，.pdf，.doc，.docx，.ppt，.pptx，.xls，.xlsx文件格式的筛选\n" +
					"	4.支持正则筛选\n" +
					"		关键字可以为正则表达式，如：&\"西安.*大学\" 可以筛选出含有“西安(任意字符)大学” 的所有结果\n" +
					"	5.操作简单\n" +
					"		对于一般关键字可以在关键字框直接输入关键字，点击任意逻辑符号按钮，将在表达式框里自动生成表达式，如：输入关键字" +
					"“西安”，点击 & 按钮，会在表达式框内生成&\"西安\"\n\n" +
					"三、关于版权\n" +
					"	此项目中引入的扩展JAR：pdfbox-app-1.8.7.jar、xmlbeans-2.3.0.jar、poi-3.11-beta3-20141111.jar、" +
					"poi-examples-3.11-beta3-2.141111.jar、poi-excelant-3.11-beta3-20141111.jar、poi-ooxml-3.11-beta3-" +
					"20141111.jar、poi-ooxml-schemas-3.11-beta3-20141111.jar、poi-scratchpad-3.11-beta3-20141111.jar。\n" +
					"	以上包的版权属于原作者，在此向开发者们致敬，感谢你们开发出如此强大的jar\n" +
					"	此项目为开源项目，可随时在http://github.com/skyfireitdiy/FindIt获取最新的源代码。\n\n" +
					"--SkyFire");
			JScrollPane scoll=new JScrollPane(aboutTxt);
			abt.add(scoll);
			
			abt.setVisible(true);
		}
	}
	
	/**
	 * 刷新 任务数量信息
	 */
	private void refThNum(){
		thNumLb.setText("当前运行中任务数量:"+String.valueOf(thNum));
	}
	
	/**
	 * 构造函数
	 */
	FindItGui(){
		/*
		 * 设置各组件属性
		 */
		super("FindIt V1.1");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 300, 600, 350);
		this.setResizable(false);
		/*
		andBt.setSize(50, 30);
		orBt.setSize(50, 30);
		notBt.setSize(50, 30);
		startBt.setSize(200,30);
		chooseBt.setSize(50,30);
		aboutBt.setSize(50,30);
		msgOut.setEditable(false);
		msgOut.setLineWrap(true);
		msgOut.setFont(new Font(null,Font.PLAIN,12));
		msgOut.add(new JScrollPane());
		*/
		
		/*
		 * 加入组件
		 */
		//setLayout(new FlowLayout());
		setLayout(null);
		this.add(allLb);
		this.add(inExpr);
		this.add(keyLb);
		this.add(inKey);
		this.add(andBt);
		this.add(orBt);
		this.add(notBt);
		this.add(chooseLb);
		this.add(inDir);
		this.add(chooseBt);
		this.add(aboutBt);
		this.add(thNumLb);
		this.add(startBt);
		this.add(pane);
		
		
		allLb.setBounds(10, 25,100, 30);
		inExpr.setBounds(120,10,450,60);
		keyLb.setBounds(10,95,50,30);
		inKey.setBounds(70,80,100,60);
		andBt.setBounds(180, 95, 50, 30);
		orBt.setBounds(240,95,50,30);
		notBt.setBounds(300,95,50,30);
		inDir.setBounds(360, 95, 150, 30);
		chooseBt.setBounds(520,95,60,30);
		aboutBt.setBounds(520, 160, 60, 30);
		thNumLb.setBounds(10, 160, 200, 30);
		startBt.setBounds(250, 160, 100, 30);
		pane.setBounds(10, 200, 580,120);
		/*
		 * 注册事件监听
		 */
		andBt.addActionListener(new andListener());
		orBt.addActionListener(new andListener());
		notBt.addActionListener(new andListener());
		chooseBt.addActionListener(new chooseListener());
		startBt.addActionListener(new startListener());
		aboutBt.addActionListener(new aboutListener());
		
		
		/*
		 * 显示组件
		 */
		this.setVisible(true);
		inExpr.setVisible(true);
		/*
		 * 刷新任务数量
		 */
		refThNum();
	}
	
	/**
	 * 获取表达式
	 * @return 表达式框中的内容
	 */
	String getExpression(){
		String str=inExpr.getText();
		inExpr.setText("");
		inKey.setText("");
		return str;
	};
	
	/**
	 * 获取目录
	 * @return 目录输入框中的内容
	 */
	String getDir(){
		String str=inDir.getText();
		inDir.setText("");
		return str;
	}
	
	/**
	 * 输出错误信息
	 * @param exp 当前表达式
	 * @param dir 当前目录
	 * @param errMsg 错误信息
	 * @param level 错误等级
	 */
	void errorOut(String exp,String dir,String errMsg,int level){
		receMsg(exp,dir,"错误："+errMsg+" 等级:"+String.valueOf(level));
		thNum-=level;
		refThNum();
	}
	
	/**
	 * 输出筛选结果
	 * @param exp 当前表达式
	 * @param dir 当前目录
	 * @param result 结果列表
	 */
	void resultOut(String exp,String dir,ArrayList<String> result){
		--thNum;
		refThNum();
		receMsg(exp,dir,"结果返回");
		JFrame resOut=new JFrame("目录:"+dir+" 表达式:"+exp+" 筛选结果");
		resOut.setBounds(300,100,800,600);
		resOut.setResizable(false);
		JList<?> resList=new JList<Object>(result.toArray());
		resList.setFont(new Font(null,Font.PLAIN,15));
		resOut.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		resOut.add(resList);
		resOut.setVisible(true);
	}
	
	/**
	 * 输出日志信息
	 * @param exp 当前表达式
	 * @param dir 当前目录
	 * @param msg 日志信息
	 */
	void receMsg(String exp,String dir,String msg){
		msgOut.setText(msgOut.getText()+(new Date())+"目录"+dir+" 表达式:"+exp+" 事件:"+msg+"\n");
		disLast();
	}
	
	/**
	 * 筛选线程原型
	 * @author SkyFire
	 *
	 */
	class startAna implements Runnable{
			@Override
			public void run() {
				FindItAnalysis FANLY=new FindItAnalysis();
				FANLY.startAnalysis(FindItGui.this);
			}
	}
	
	/**
	 * 自动滚动日志
	 */
	private void disLast(){
		msgOut.setCaretPosition(msgOut.getText().length());
	}
}