package task.util;

import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
  
/**  
 * 使用jasperReport做报表时的工具支持类.有两个用途,生成jasperPrint对象,和设置导出时的session  
 */  
@SuppressWarnings("deprecation")
public class ReportUtils {   
    /**  
     * 获得JasperPrint对象;自定义填充报表时的parameter和dataSource. 参数说明和动态表头的用法参考上一方法  
     * @return  
     */  
    @SuppressWarnings("rawtypes")
	public static JasperPrint getJasperPrint(Map<String, Object> parameter,Collection data) throws JRException { 
    	JasperReport jasperReport = JasperCompileManager.compileReport(ReportUtils.class.getResourceAsStream("/reports/taskTemplete.jrxml"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameter, new JRBeanCollectionDataSource(data)); 
        return jasperPrint;
    }   
    
    @SuppressWarnings("rawtypes")
	public static void export(OutputStream outputStream, Map<String, Object> parameter,Collection data,DocType type) throws Exception{
    	JasperPrint jasperPrint = getJasperPrint(parameter, data);
    	JRAbstractExporter exporter = getJRExporter(type);   
    	exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);   
    	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);   
    	exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
        exporter.exportReport();   
        outputStream.flush();  
	    outputStream.close();
    }
  
    /**  
     * 传入类型，获取输出器  
     *   
     * @param docType  
     * @return  
     */  
    @SuppressWarnings("rawtypes")
	public static JRAbstractExporter getJRExporter(DocType docType) {   
        JRAbstractExporter exporter = null;   
        switch (docType) {   
        case PDF:   
            exporter = new JRPdfExporter();   
            break;   
        case HTML:   
            exporter = new JRHtmlExporter();   
            break;   
        case XLS:   
            exporter = new JRXlsxExporter();   
            break;   
        case XML:   
            exporter = new JRXmlExporter();   
            break;   
        case RTF:   
            exporter = new JRRtfExporter();   
            break;   
        }   
        return exporter;   
    }   
  
    /**  
     * 定义了报表输出类型，固定了可输出类型  
     *   
     * @author Administrator  
     *   
     */  
    public static enum DocType {   
        PDF, HTML, XLS, XML, RTF   
    }   
  
}