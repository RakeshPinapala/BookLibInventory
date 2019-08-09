package com.aryans.library.controller.ViewModelMapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.*;

/**
 * This Class is for performing transformations if required from service/view layer to the model. 
 * Do Not change the sequence of the values assigned, unless there is a change in the model.
 * 
 * @author Shinchan
 *
 */
@Component
public class UserViewModelMapper {
	
	public Object[] mapUserInsertion(List serviceValues) {
		Object[] objValues = serviceValues.toArray();
		objValues[2]=BCrypt.hashpw((String) serviceValues.get(2), BCrypt.gensalt());
		return objValues;
	}
}
