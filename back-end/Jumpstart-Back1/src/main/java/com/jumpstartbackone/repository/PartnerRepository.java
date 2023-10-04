package com.jumpstartbackone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumpstartbackone.entity.User;



//import com.mealsonwheels.entity.Preparation;

//import com.mealsonwheels.entity.Partner;



@Repository
public interface PartnerRepository extends JpaRepository<User, Long>{

//	@Query("SELECT user FROM User user WHERE user.role_id = 3")
//	public List<User> showAllRiders();
	

	List<User> findByRoles_Id(Long roleId);//findbyRoleId to findByRoles_Id
	 
//	 List<Preparation> findByUserAndOrder(User user, Meal meal);

	 int countByRoles(Long roleId);//countByRoleId to countByRoles
	 
//	 List<Preparation> findByPartner(User partner);
	 
//	 List<Preparation> findByUser_Id(Long userId);
	 
	 
	 
}