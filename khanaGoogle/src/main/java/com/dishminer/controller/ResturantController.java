/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.controller;

import com.dishminer.resturants.tasks.IResturantDO;
import com.dishminer.resturants.tasks.ITask;
import com.dishminer.resturantsservice.APIMetadataBO;
import com.dishmer.taksrequest.BaseRequest;
import java.util.List;

/**
 *
 * @author agupta6
 */
public class ResturantController {
    
    
    
    
    private ITask strategy;
    private APIMetadataBO apiMetaDataBO;
    private BaseRequest request;
 
    public ResturantController(ITask strategy , APIMetadataBO BO , BaseRequest request) {
        this.strategy = strategy;
        this.apiMetaDataBO = BO;
        this.request = request;
    
    }
    public List<IResturantDO> executeStrategy() {
        return this.strategy.execute(apiMetaDataBO ,  request);
    }
}
    
    
    
    

