package com.sunrise.base.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Util {
	// 水印透明度
	private static float alpha = 0.5f;
	// 水印横向位置
	private static int positionWidth = 150;
	// 水印纵向位置
	private static int positionHeight = 300;
	// 水印文字字体
	private static Font font = new Font("宋体", Font.BOLD, 30);
	// 水印文字颜色
	private static Color color = Color.red;

	/**
	 * 2010-04-26新加，显示水印的util 开始
	 * */
	public static BufferedImage rotateImg(BufferedImage image, int degree) {

		int iw = image.getWidth();// 原始图象的宽度
		int ih = image.getHeight();// 原始图象的高度
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		degree = degree % 360;
		if (degree < 0)
			degree = 360 + degree;// 将角度转换到0-360度之间
		double ang = degree * 0.0174532925;// 将角度转为弧度

		/**
		 * 确定旋转后的图象的高度和宽度
		 */

		if (degree == 180 || degree == 0 || degree == 360) {
			w = iw;
			h = ih;
		} else if (degree == 90 || degree == 270) {
			w = ih;
			h = iw;
		} else {
			int d = iw + ih;
			w = (int) (d * Math.abs(Math.cos(ang)));
			h = (int) (d * Math.abs(Math.sin(ang)));
		}

		x = (w / 2) - (iw / 2);// 确定原点坐标
		y = (h / 2) - (ih / 2);
		BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		Graphics gs = rotatedImage.getGraphics();
		gs.setColor(new Color(1.0F, 0.75F, 0.0F, 0.5F));
		gs.fillRect(0, 0, w, h);// 以给定颜色绘制旋转后图片的背景
		AffineTransform at = new AffineTransform();
		at.rotate(ang, w / 2, h / 2);// 旋转图象
		at.translate(x, y);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		op.filter(image, rotatedImage);
		image = rotatedImage;
		return image;

		/*
		 * Graphics g = image.createGraphics(); g.drawImage(image, 0, 0, x, y,
		 * null); String s="www.143zh.com"; g.setColor(Color.RED); Font f = new
		 * Font("微软雅黑", 20, 50); g.setFont(f );
		 * 
		 * g.drawString("水印文字", x / 5, y / 5); g.dispose();
		 */
		// BufferedImage rotatedImage=new BufferedImage(w,h,image.getType());
		// Graphics2D g2d = rotatedImage.createGraphics();
		// rotatedImage = g2d.getDeviceConfiguration().createCompatibleImage(w,
		// h, Transparency.TRANSLUCENT);
		// g2d.dispose();
		// g2d = rotatedImage.createGraphics();
		// g2d.dispose();
		// Graphics gs=rotatedImage.getGraphics();
		// gs.setColor( new Color(1.0F, 0.75F, 0.0F, 0.0F));
		// gs.fillRect(0,0,w,h);
		// g2d.setColor(bgcolor);
		// g2d.setStroke(new BasicStroke(1));
		// g2d.drawString("水印文字", x / 5, y / 5);
		// g2d.rotate(Math.toRadians(degree),(double) rotatedImage.getWidth() /
		// 2, (double) rotatedImage.getHeight() / 2);

		// AffineTransform at=new AffineTransform();
		// at.rotate(ang,w/2,h/2);//旋转图象
		// at.translate(x,y);
		// AffineTransformOp op=new
		// AffineTransformOp(at,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		// op.filter(image, rotatedImage);
		// image=rotatedImage;
		// return image;
	}

	/**
	 * 图片水印
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param targetImg
	 *            目标图片
	 * @param x
	 *            修正值 默认在中间
	 * @param y
	 *            修正值 默认在中间
	 * @param alpha
	 *            透明度
	 */
	public final static void pressImage(String pressImg, String targetImg, int x, int y, float alpha) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao,
					null);
			// 水印文件结束
			g.dispose();
			ImageIO.write(image, "jpg", img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static BufferedImage getPressImage(String pressImg, String targetImg, int x, int y, float alpha) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao,
					null);
			// 水印文件结束
			g.dispose();
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param targetImg
	 *            目标图片
	 * @param fontName
	 *            字体名称
	 * @param fontStyle
	 *            字体样式
	 * @param color
	 *            字体颜色
	 * @param fontSize
	 *            字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度
	 */
	public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);

			g.dispose();

			g = image.createGraphics();
			g.setColor(new Color(255, 0, 0));

			g.setStroke(new BasicStroke(1));
			g.setColor(new Color(255, 0, 0));
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "png", img);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param targetImg
	 *            目标图片
	 * @param fontName
	 *            字体名称
	 * @param fontStyle
	 *            字体样式
	 * @param color
	 *            字体颜色
	 * @param fontSize
	 *            字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度
	 */
	public static BufferedImage getPressText(String pressText, BufferedImage image, String fontName, int fontStyle,
			Color color, int fontSize, int x, int y, float alpha) {
		try {
			Graphics2D g = image.createGraphics();
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawString(pressText, (image.getWidth() - (getLength(pressText) * fontSize)) / 2 + x,
					(image.getHeight() - fontSize) / 2 + y);
			g.dispose();
			return image;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 给图片添加水印文字、可设置水印文字的旋转角度
	 * 
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 */
	public static void markImageByText(String logoText, BufferedImage image, Integer degree) {

		InputStream is = null;
		OutputStream os = null;

		// 1、源图片
		BufferedImage buffImg = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		// 2、得到画笔对象
		Graphics2D g = buffImg.createGraphics();
		// 3、设置对线段的锯齿状边缘处理
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image.getScaledInstance(image.getWidth(null), image.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
				null);
		// 4、设置水印旋转
		if (null != degree) {
			g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
		}
		// 5、设置水印文字颜色
		g.setColor(color);
		// 6、设置水印文字Font
		g.setFont(font);
		// 7、设置水印文字透明度
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
		// 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
		g.drawString("ss", positionWidth, positionHeight);
		// 9、释放资源
		g.dispose();
		// 10、生成图片
		try {
			os = new FileOutputStream(
					new File(
							"D:\\Workspaces\\sts\\CMWMBOSPortalBase\\src\\main\\webapp\\resources\\login\\images\\securityimage.png"));
			ImageIO.write(buffImg, "jpg", os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("图片完成添加水印文字");

	}

	public static void main(String[] args) throws IOException {
		String path = "D:\\Workspaces\\sts\\CMWMBOSPortalBase\\src\\main\\webapp\\resources\\login\\images\\security_image.png";
		String path1 = "D:\\Workspaces\\sts\\CMWMBOSPortalBase\\src\\main\\webapp\\resources\\login\\images\\securityimage.png";

		ImageIcon imgIcon = new ImageIcon(path);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null);
		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bimage.createGraphics();
		g.drawImage(theImg, 0, 0, null);
		g.rotate(Math.toRadians(-45), (double) bimage.getWidth() / 2, (double) bimage.getHeight() / 2);
		// g.setBackground(Color.white);
		// g.setColor(Color.red);
		g.setFont(new Font("黑体", Font.BOLD, 24));
		g.setColor(new Color(227, 227, 227, 120));
		// g.setColor(new Color(227,227,227,100));
		g.drawString("admin", width / 5 + 50, height / 3);
		g.drawString("2014-01-24 10:00:01", width / 4 - 50, height / 2);
		g.drawString("全国", width / 3, height / 2 + 50);
		g.dispose();
		ImageIO.write(bimage, "PNG", new File(path1));
	}

	/**
	 * 缩放
	 * 
	 * @param filePath
	 *            图片路径
	 * @param height
	 *            高度
	 * @param width
	 *            宽度
	 * @param bb
	 *            比例不对时是否需要补白
	 */
	@SuppressWarnings("static-access")
	public static void resize(String filePath, int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
			File f = new File(filePath);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	/** 2010-04-26新加，显示水印的util 结束 */
	/**
	 * 获取当前选择周对应的日期
	 * 
	 * @param year
	 *            年
	 * @param weekCount
	 *            选择周数
	 * @return 返回对应日期
	 */
	public static String getSelectWeekCountToDate(int year, int weekCount) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, weekCount);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		c.roll(Calendar.DAY_OF_WEEK, 7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(c.getTime()).toString();
		return sdf.format(c.getTime()).toString();
	}

	/**
	 * 获取当前日期对应周
	 * 
	 * @return 返回当前日期对应周数
	 */
	public static Integer getWeekOfYear() {
		Calendar c = Calendar.getInstance();
		int weekint = c.get(Calendar.WEEK_OF_YEAR);

		return Integer.valueOf(weekint);
	}

	/**
	 * 计算增长率
	 * 
	 * @return
	 */
	public static String getGrowthRate(String value, String valueLd) {
		BigDecimal big = new BigDecimal(value);
		String s;
		if (new BigDecimal(valueLd).doubleValue() == 0) {
			return "--";
		} else if (big.doubleValue() == 0) {
			return "0.00%";
		}
		Double rate = big.divide(new BigDecimal(valueLd), 10, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
				.doubleValue();
		if ((rate < 1 && rate > 0) || (rate < 0 && rate > -1)) {
			s = new DecimalFormat("0.00").format(rate) + "%";
		} else {
			s = new DecimalFormat(",###.00").format(rate) + "%";
		}

		return s;
	}

	/**
	 * 转换单位为万，并保留小数点2位，四舍五入。
	 * 
	 * @param value
	 * @return
	 */
	public static String getFeeStrOrCntStr(String value) {
		double fee;
		BigDecimal big = new BigDecimal(value);
		// if(big.doubleValue()<10000 && big.doubleValue()>-10000){
		// fee = big.doubleValue();
		// if(fee == 0){
		// return "0";
		// }
		// return new DecimalFormat("###.00").format(fee);
		// }
		fee = big.divide(new BigDecimal(Integer.toString(10000)), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return new DecimalFormat("0.00").format(fee);
	}

}
