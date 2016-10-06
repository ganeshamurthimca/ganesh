/**
 * 
 */
package com.davos.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.davos.domain.User;
import com.davos.domain.UserRoleMapping;
import com.davos.dto.UserDTO;
import com.davos.dto.UserRoleMappingDTO;
import com.davos.repository.UserRoleMappingRepository;
import com.davos.service.UserRoleMappingService;
import com.davos.status.ReturnStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author vigneshwaran.b
 *
 */
@Service
public class UserRoleMappingServiceImpl implements UserRoleMappingService {

	@Autowired
	private UserRoleMappingRepository userRoleMappingRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;

	private Logger logger = LoggerFactory.getLogger(UserRoleMappingServiceImpl.class);

	@Override
	public ReturnStatus addUserRoleMapping(UserRoleMappingDTO userRoleMappingDTO) {
		try {
			UserRoleMapping userRoleMapping = modelMapper.map(userRoleMappingDTO, UserRoleMapping.class);
			if (userRoleMappingRepository.save(userRoleMapping) != null) {
				return ReturnStatus.SUCCESS;
			}
		} catch (DataIntegrityViolationException ex) {
			logger.error(ex.getMessage());
			return ReturnStatus.ALREADYEXISTS;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ReturnStatus.FAILURE;
		}
		return ReturnStatus.OTHER;
	}

	@Override
	public List<UserRoleMappingDTO> findByUserId(UserDTO userDTO) {
		List<UserRoleMappingDTO> userRoleMappingDTOs = new ArrayList<>();
		try {
			List<UserRoleMapping> userRoleMappings = userRoleMappingRepository
					.findByUserId(modelMapper.map(userDTO, User.class));

			userRoleMappings.forEach(userRoleMapping -> {
				try {
					String resultJson = objectMapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(userRoleMapping);
					UserRoleMappingDTO userRoleMappingDTO = objectMapper.readValue(resultJson,
							UserRoleMappingDTO.class);
					userRoleMappingDTOs.add(userRoleMappingDTO);
				} catch (Exception ex) {
					logger.info(ex.getMessage());
				}
			});
			return userRoleMappingDTOs;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public List<UserRoleMappingDTO> getAllUserRoleMappingDTO() {
		List<UserRoleMappingDTO> userRoleMappingDTOs = new ArrayList<>();
		try {
			List<UserRoleMapping> userRoleMappings = userRoleMappingRepository.findAll();
			userRoleMappings.forEach(userRoleMapping -> userRoleMappingDTOs
					.add(modelMapper.map(userRoleMapping, UserRoleMappingDTO.class)));
			return userRoleMappingDTOs;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return null;
	}
}
