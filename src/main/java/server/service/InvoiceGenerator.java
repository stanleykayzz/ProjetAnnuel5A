package server.service;

import com.lowagie.text.DocumentException;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.util.IOUtil;
import java.io.*;
import java.time.LocalDate;
import  java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alvin.ondzounga on 03/07/2017.
 */
public class InvoiceGenerator {
    String tableRow;
    String rowFilePath;
    String InvoiceDate = "invoiceDate";
    String pattern = "dd/MM/yyyy";
    String dateInString = new SimpleDateFormat(pattern).format(new Date());
    String InvoiceClientName = "clientFullname";
    String InvoiceClientEmail = "clientEmail";
    String InvoiceClientCity = "clientCity";
    String InvoiceClientCountry = "clientCountry";
    String InvoiceClientPhone = "clientPhone";
    String InvoiceClientAddress = "clientAddress";
    String InvoiceClientBirthday = "clientBirthday";
    String fileNameWithPath;
    String fileNameWithPath2;
    String fileDestination;
    String upperHtml;
    String lowerHtml;
    Client client;
    Booking reservation;
    List<Booking> ListdeReservation;
    String finalHtml;

    //constructeur avec liste de res
    public InvoiceGenerator(String upperSource,String rowSource, String lowerSource, String destination, Client c, List<Booking> list){
        this.fileNameWithPath = upperSource;
        this.rowFilePath = rowSource;
        this.fileNameWithPath2 = lowerSource;
        this.fileDestination = destination;
        this.client = c;
        this.ListdeReservation = list;
        this.init();
    }

    private void init() {
        this.upperHtml = convertFileHtmlToString(this.fileNameWithPath);
        this.tableRow = convertFileHtmlToString(this.rowFilePath);
        this.lowerHtml = convertFileHtmlToString(this.fileNameWithPath2);
        System.out.println(" la date du jour : " + dateInString);
        this.upperHtml = replaceValueInText(this.upperHtml, this.InvoiceDate, dateInString);
        this.upperHtml = replaceValueInText(this.upperHtml, this.InvoiceClientName, this.client.nom+" "+this.client.prenom);
        this.upperHtml = replaceValueInText(this.upperHtml, this.InvoiceClientEmail, this.client.email);
        this.upperHtml = replaceValueInText(this.upperHtml, this.InvoiceClientCity, this.client.ville);
        this.upperHtml = replaceValueInText(this.upperHtml, this.InvoiceClientCountry, this.client.pays);
        this.upperHtml = replaceValueInText(this.upperHtml, this.InvoiceClientPhone, this.client.phone);
        this.upperHtml = replaceValueInText(this.upperHtml, this.InvoiceClientAddress, this.client.adresse);
        this.upperHtml = replaceValueInText(this.upperHtml, this.InvoiceClientBirthday, this.client.anniversaire);

        double totalCount=0;
        //String toAdd=tableRow;
        //ici on ajoute les infos de chambre
        for (Booking b: this.ListdeReservation) {
            String toAdd = tableRow;
            toAdd = replaceValueInText(toAdd,"roomType", b.chambre)
                    .replace("roomCount",""+b.quantité)
                    .replace("roomCheckin",b.arrivé)
                    .replace("RoomCheckout",b.départ)
                    .replace("countNights",""+b.nuit)
                    .replace("roomPrice", ( ""+b.prixNuit))
                    .replace("roomTotalPrice",""+b.total);

            this.upperHtml = this.upperHtml + toAdd;
            System.out.println(toAdd);

            totalCount = totalCount +b.total;
        }
        this.finalHtml = upperHtml + lowerHtml;
        System.out.println(finalHtml);

        //remplace le prix total de la facture dans le fichier html comple
        this.finalHtml = this.finalHtml.replace("invoiceTotal",""+totalCount);
        convertStringToPdf(this.finalHtml);
    }

    private String convertFileHtmlToString(String src){
        System.out.println(" la conversion du fichier en string");
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(src));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        return contentBuilder.toString();
    }
    private String convertFileHtmlToString(){
        System.out.println(" la conversion du fichier en string");
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileNameWithPath));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        return contentBuilder.toString();
    }

    private String replaceValueInText(String content, String old,String nevv){
        String value = content.replace(old,nevv);
        return value;
    }

    private void convertStringToPdf(String s){
        // if you have html source in hand, use it to generate document object
        Document document = XMLResource.load( new ByteArrayInputStream(s.getBytes())).getDocument();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument( document, null );
        renderer.layout();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream( fileDestination );
            renderer.createPDF( fos );
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "File 1: '" + fileDestination + "' created." );
    }

    public static class Client{
        String nom;
        String prenom;
        String anniversaire;
        String phone;
        String email;
        String ville;
        String pays;
        String adresse;
        String CP;

        public Client(String n,String p,String e,String cp,String pay,String v,String anniv,String phone,String a){
            this.nom = n;
            this.adresse = a;
            this.anniversaire = anniv;
            this.CP = cp;
            this.email = e;
            this.pays = pay;
            this.prenom = p;
            this.ville = v;
            this.phone = phone;
        }

    }
    public static class Booking{
        String chambre;
        int quantité;
        String arrivé;
        String départ;
        int nuit;
        double prixNuit;
        double total;
        double invoiceTotal;

        public Booking(String type,int q,String a,String d,double prix){
            this.chambre = type;
            this.quantité = q;
            this.arrivé = a;
            this.départ = d;
            this.prixNuit = prix;
            setNbreNuit();
            setTotal();
        }

        private void setInvoiceTotal(double p) {
            this.invoiceTotal = p;
        }

        private double getInvoiceTotal() {
            return this.invoiceTotal;
        }

        private void setTotal() {
            this.total = (this.quantité * this.prixNuit)*getNbreNuit();
        }

        private double getNbreNuit() {
            return this.nuit;
        }

        private void setNbreNuit() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date date1 = formatter.parse(this.arrivé);
                Date date2 = formatter.parse(this.départ);
                long diff = date2.getTime() - date1.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);
                System.out.print(diffDays + " days, ");
                final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
                int diffInDays = (int) ((date2.getTime() - date1.getTime())/ DAY_IN_MILLIS );
                this.nuit = diffInDays;
                System.out.println("un tota de jours de "+this.nuit);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
            //On récupère les infos des client et on les ajoute dans un objet
            InvoiceGenerator.Client al = new InvoiceGenerator.Client("Ondzounga","Alvin","ondzoungabruce@hotmail.fr","92190","France","Meudon","25/03/1994","0643329404","35 route de vaugirard");
            InvoiceGenerator.Client max = new InvoiceGenerator.Client("Mollard","Maxime","maxime.mollard75@hotmail.fr","75015","France","Paris","25/03/1994","0643329404","35 route de vaugirard");
            InvoiceGenerator.Client kev = new InvoiceGenerator.Client("Vivor","Kévin","KevOR@hotmail.fr","93190","France","Val de fontenay","25/03/1994","0643329404","35 route de vaugirard");

            InvoiceGenerator.Booking reservation = new InvoiceGenerator.Booking("Chambre Double",1,"13/11/2017","23/12/2017",100000);
            InvoiceGenerator.Booking r2 = new InvoiceGenerator.Booking("Chambre Double",3,"23/11/2017","26/12/2017",100000);
            InvoiceGenerator.Booking r3 = new InvoiceGenerator.Booking("Suite Junior",2,"15/11/2017","23/12/2017",150000);
            InvoiceGenerator.Booking r4 = new InvoiceGenerator.Booking("Suite Executive",5,"02/11/2017","23/12/2017",200000);

            List<InvoiceGenerator.Booking> listDeRes = new ArrayList<InvoiceGenerator.Booking>();
            listDeRes.add(reservation);
            listDeRes.add(r2);
            listDeRes.add(r3);
            listDeRes.add(r4);

            String upperTemplate = "templates/velocity/invoiceUpperTemplate.html";
            String rowTemplate = "templates/velocity/invoiceRowTemplate.html";
            String lowerTemplate = "templates/velocity/invoiceLowerTemplate.html";
            String pdfFilePath = "C:\\Users\\alvin.ondzounga\\Downloads\\RHM_Invoice.pdf";
            InvoiceGenerator C = new InvoiceGenerator(upperTemplate,rowTemplate,lowerTemplate,pdfFilePath,al,listDeRes);

    }
}