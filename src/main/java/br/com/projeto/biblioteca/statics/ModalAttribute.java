package br.com.projeto.biblioteca.statics;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public class ModalAttribute {

   static public Model setAttribute(Page<?> data, Model model , String type){
        model.addAttribute("console", type);
        if(data == null){
            model.addAttribute("data", data);
            model.addAttribute("pagination", 0);
            model.addAttribute("total", 0);
            model.addAttribute("paginationAtual", 0);
        }else {
            model.addAttribute("data", data);
            model.addAttribute("pagination", data.getTotalPages() - 1);
            model.addAttribute("total", data.getTotalElements());
            model.addAttribute("paginationAtual", data.getPageable().getPageNumber());
        }
        return model;
    }

   static public Model setAttributeWithoutPage(Optional<?> data, Model model , String type){
        model.addAttribute("console", type);
        if(data.isPresent()){
            model.addAttribute("data", data.get());
        }else {
            model.addAttribute("data", null);
        }
        return model;
    }

    static public Model setAttributeList(List<?> data, Model model , String type){
        model.addAttribute("console", type);
        model.addAttribute("data", data);
        return model;
    }
}
