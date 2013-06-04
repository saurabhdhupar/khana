/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dishminer.resturants.tasks;

import com.dishmer.taksrequest.BaseRequest;
import com.dishminer.resturantsservice.APIMetadataBO;
import java.util.List;

/**
 *
 * @author agupta6
 */
public interface ITask {

public List<IResturantDO> execute(APIMetadataBO Bo , BaseRequest request);    
    
    
}
