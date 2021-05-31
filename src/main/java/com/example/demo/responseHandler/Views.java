package com.example.demo.responseHandler;

public class Views {

    /*Basic views*/
    public interface DataView {
    }

    public interface PageableView extends DataView {
    }

    public interface BranchOfficeView extends DataView {
    }

    public  interface BranchOfficeViewPageable extends PageableView {}

}