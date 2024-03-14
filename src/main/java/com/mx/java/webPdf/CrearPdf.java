/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.webPdf;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.*;
import com.mx.java.domain.Producto;
import com.mx.java.service.ProductoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.text.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author tonny
 */
@Controller
@Component("venta/NuevaVenta")
public class CrearPdf extends AbstractPdfView {

    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US);
    DateFormat dateFormat = new SimpleDateFormat("EEEEEEEEE, d MMM yyyy, HH:mm:ss ");
    Image imagen = null;
    double subtotal = 0;
    double total = 0;    
   
    @Autowired
    private ProductoService productoService;
     
      ArrayList<Producto> productos = new ArrayList<>();

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

     productos = (ArrayList<Producto>) model.get("productos");

        document.setPageSize(PageSize.A6);
        document.setMargins(-25, -25, 15, 20);
        document.open();

        PdfPCell celda = null;
        /*Tabla titulo*/
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Color.BLACK);
        Font fuenteLetra = FontFactory.getFont(FontFactory.TIMES, 15, Color.BLACK);
        PdfPTable tablatitulo = new PdfPTable(3);
        tablatitulo.setWidths(new float[]{0.6f, 2.2f, 0.4f});

        imagen = imagen.getInstance("C:\\ProyectsSpring\\SuperTienda\\src\\main\\resources\\static\\image\\tienda.png");
        celda = new PdfPCell(new Phrase(imagen.toString()));
        celda.addElement(imagen);
        celda.setBorder(2);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_LEFT); //
        celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        tablatitulo.addCell(celda);

        //Titulo Tabla
        celda = new PdfPCell(new Phrase("MANCOS", fuenteTitulo));
        celda.setBackgroundColor(Color.white);
        celda.setPadding(6);
        celda.setBorder(2);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); //alinear centro
        celda.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        tablatitulo.addCell(celda);

        //Tabla Imagen
        imagen = imagen.getInstance("C:\\ProyectsSpring\\SuperTienda\\src\\main\\resources\\static\\image\\venta.png");
        imagen.scaleAbsolute(40, 10);
        celda = new PdfPCell(new Phrase(imagen.toString()));
        celda.addElement(imagen);
        celda.setBorder(2);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT); //
        celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        tablatitulo.addCell(celda);

        PdfPTable tablaMancos = new PdfPTable(1);
        //Titulo Mancos
        celda = new PdfPCell(new Phrase("Tel:55-81-10-12-85"));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tablaMancos.addCell(celda);

        String date = dateFormat.format(new Date());
        celda = new PdfPCell(new Phrase(date));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tablaMancos.addCell(celda);

        celda = new PdfPCell(new Phrase("Valle de chalco,Edomex,Mexico"));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tablaMancos.addCell(celda);

        tablaMancos.setSpacingAfter(20);

        //Tabla de la venta
        PdfPTable tablaventa = new PdfPTable(3);
        tablaventa.setWidths(new float[]{0.15f, 0.7f, 0.3f});

        celda = new PdfPCell(new Phrase("Cant."));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        tablaventa.addCell(celda);

        celda = new PdfPCell(new Phrase("Producto."));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        tablaventa.addCell(celda);

        celda = new PdfPCell(new Phrase("Importe.",fuenteLetra));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        tablaventa.addCell(celda);

        for (Producto producto : productos) {

            celda = new PdfPCell(new Phrase(producto.getExistencia().toString()));
            celda.setBorder(0);
            celda.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
            tablaventa.addCell(celda);

            celda = new PdfPCell(new Phrase(producto.getNombre()));
            celda.setBorder(0);
            celda.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
            tablaventa.addCell(celda);

            subtotal = producto.getExistencia() * producto.getPrecio();
            String formatSubTotal = formatoMoneda.format(subtotal);
            celda = new PdfPCell(new Phrase(formatSubTotal));
            celda.setBorder(0);
            celda.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
            tablaventa.addCell(celda);
            total = total + subtotal;

            var productoAct = productoService.BuscarProducto(producto);
            if (productoAct.getIdProducto() == producto.getIdProducto()) {
                var existenciaNueva = productoAct.getExistencia() - producto.getExistencia();

                productoAct.setExistencia(existenciaNueva);
                productoService.Guardar(productoAct);
            }
        }

        PdfPTable tablaTotal = new PdfPTable(2);
        tablaTotal.setWidths(new float[]{0.8f, 0.8f});

        celda = new PdfPCell(new Phrase("total"));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        tablaTotal.addCell(celda);

        String formatSubTotal = formatoMoneda.format(total);
        celda = new PdfPCell(new Phrase(formatSubTotal));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        tablaTotal.addCell(celda);
        tablaTotal.setSpacingBefore(15);

        PdfPTable tablaMensaje = new PdfPTable(1);
        imagen = imagen.getInstance("C:\\ProyectsSpring\\SuperTienda\\src\\main\\resources\\static\\image\\gracias.png");
        imagen.scaleAbsolute(50, 50);
        imagen.setAlignment(Element.ALIGN_CENTER);
        celda.addElement(imagen);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tablaMensaje.addCell(celda);

        celda = new PdfPCell(new Phrase("!!Gracias Por su compra!!"));
        celda.setBorder(0);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tablaMensaje.addCell(celda);
        tablaMensaje.setSpacingBefore(15);

        document.add(tablatitulo);
        document.add(tablaMancos);
        document.add(tablaventa);
        document.add(tablaTotal);
        document.add(tablaMensaje);
        subtotal = 0;
        total = 0;
        document.close();
     
    }
   
}
