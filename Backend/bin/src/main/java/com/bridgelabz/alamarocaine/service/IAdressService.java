package com.bridgelabz.alamarocaine.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bridgelabz.alamarocaine.dto.AddressDto;
import com.bridgelabz.alamarocaine.dto.UpdateAddressDto;
import com.bridgelabz.alamarocaine.entity.Address;
import com.bridgelabz.alamarocaine.entity.Users;

@Repository
public interface IAdressService {

	Address addAddress(AddressDto address, String token);

	Users deleteAddress(String token, Long addressId);

	Address updateAddress(UpdateAddressDto address, String token);

	List<Address> getAllAddress();

	Address getAddress(Long id);

	List<Address> getAddressByUserId(String token);

	Address getAddress(String type, String token);

}
