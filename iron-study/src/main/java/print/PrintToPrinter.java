package print;

import java.awt.*;
import java.awt.print.*;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 14:55
 * @package: print
 * @description:
 */
public class PrintToPrinter {

    public static void main(String[] args) throws Exception {
        Book book = new Book();
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);

        Paper p = new Paper();
        p.setSize(590, 840);
        p.setImageableArea(10, 10, 590, 840);
        pf.setPaper(p);
        book.append(new OneLabel(), pf);

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(book);
        job.print();
    }

    /**
     * 实现Printable即可打印
     */
    public static class OneLabel implements Printable {
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex != 0) {
                // 此例子只有一页；
                return NO_SUCH_PAGE;
            }
            Graphics2D g2 = (Graphics2D) graphics;
            double scale = 72.0 / 300; //屏幕DPI是72；打印机DPI：300； 可以实现打印的更精细，特别是图片
            g2.scale(scale, scale);

            g2.setColor(Color.black);

            double x = pageFormat.getImageableX();
            double y = pageFormat.getImageableY();
            System.out.println("左上角：" + x + "," + y + "  宽高: " + pageFormat.getWidth() + "," + pageFormat.getHeight());

            String str = "中文字符串";
            Font font = new Font("微软雅黑", Font.PLAIN, 10);
            g2.setFont(font);
            g2.drawString(str, (float) x, (float) (y + 20));

            Font font2 = new Font("微软雅黑", Font.PLAIN, 20);
            g2.setFont(font2);
            g2.drawString(str, (float) x, (float) (y + 80));

            return PAGE_EXISTS;
        }
    }
}
