package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;

@Named(value = "fileUploadFormBean")
@ViewScoped
public class FileUploadFormBean implements Serializable {

    private Part image;
    private String url;
    
    private String name;

    public FileUploadFormBean() {
        url = "http://localhost:8085/scolag/resources/images/";
    }

    public void doUpload() {
        try {
            InputStream in = image.getInputStream();
            File f = new File("D:\\Documents\\NetBeansProjects\\scolag\\web\\resources\\images\\" + image.getSubmittedFileName());
            f.createNewFile();

            FileOutputStream out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.close();

            in.close();
        } catch (Exception e) {
        }
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
