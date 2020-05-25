import com.sun.mail.iap.ByteArray;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sun.misc.IOUtils;
@ManagedBean(name="fileUpload")
@SessionScoped
public class FileUploadVisao implements Serializable{
    private UploadedFile arquivo;
    private String texto;
    public String getTexto(){
        return texto;
    }
    public void setTexto(String texto){
        this.texto=texto;
    }
    public UploadedFile getArquivo(){
        return arquivo;
    }
    public void setArquivo(UploadedFile arquivo){
        this.arquivo=arquivo;
    }
    public void realizarUpload(FileUploadEvent event){
        this.arquivo=event.getFile();
        try{
            byte[] conteudo=IOUtils.readFully(arquivo.getInputstream(),arquivo.getInputstream().available(),true);
            texto=new String(conteudo);
            FacesContext context=FacesContext.getCurrentInstance();
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso",event.getFile().getFileName() + " foi feito o Upload."));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}