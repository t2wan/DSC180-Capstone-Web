package com.controller;

import com.entity.Result;
import com.service.FileService;
import com.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class IndexController {
    @Autowired
    FileService fileService;

    //@ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index()
    {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value="/fileUpload",method = RequestMethod.POST)
    public Result fileUp(MultipartFile file)
    {
        return fileService.fileTransfer(file);
    }

    @RequestMapping(value="/run",method = RequestMethod.GET)
    public String run()
    {
        System.out.println("/run");
        return "runOutput";
    }

    @Autowired
    PythonService pythonService;

    @ResponseBody
    @RequestMapping(value="/running",method = RequestMethod.POST)
    public String running()
    {
        pythonService.runPython();
        return "success";
    }

    @RequestMapping(value="/result",method = RequestMethod.GET)
    public String result(){
        return "result";
    }

    @ResponseBody
    @RequestMapping(value="/getAutoPhrase",method = RequestMethod.POST)
    public Result getAutoPhrase(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("getAutoPhrase");
        return fileService.fileRead(request.getServletContext().getRealPath("/"));
    }
}
