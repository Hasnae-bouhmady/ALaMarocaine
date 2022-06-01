package com.bridgelabz.alamarocaine.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.alamarocaine.dto.AddressDto;
import com.bridgelabz.alamarocaine.dto.UpdateAddressDto;
import com.bridgelabz.alamarocaine.entity.Address;
import com.bridgelabz.alamarocaine.entity.Users;
import com.bridgelabz.alamarocaine.repository.AddressRepository;
import com.bridgelabz.alamarocaine.repository.UserRepository;
import com.bridgelabz.alamarocaine.service.IAdressService;
import com.bridgelabz.alamarocaine.util.JwtGenerator;

@Service
public class AdressImpService implements IAdressService {
	@Autowired
	private JwtGenerator generate;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepository;

	@Transactional
	@Override
	public Address addAddress(AddressDto address, String token) {

		Long id = generate.parseJWT(token);
		Address add = new Address(address);
		System.out.println(add.getAddress() + "->" + add.getCity());
		Users userdetails = userRepo.findById(id).orElseThrow(null);
		userdetails.getAddress().add(add);
		return addressRepository.save(add);

	}

	@Override
	public Address updateAddress(UpdateAddressDto addressupdate, String token) {
		List<Address> list = new ArrayList<>();

		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).orElseThrow(null);
		Address add = addressRepository.findById(addressupdate.getAddressId()).get();

		add.setAddressId((addressupdate.getAddressId()));
		add.setAddress(addressupdate.getAddress());
		add.setLocality(addressupdate.getLocality());
		add.setCity(addressupdate.getCity());
		add.setCountry(addressupdate.getCountry());
		add.setLandmark(addressupdate.getLandmark());
		add.setPincode(addressupdate.getPincode());
		add.setState(addressupdate.getState());
		add.setCountry(addressupdate.getCountry());
		add.setPhoneNumber(addressupdate.getPhoneNumber());
		add.setAddressType(addressupdate.getAddressType());
		;
		addressRepository.save(add);
		userdetails.getAddress().add(add);
		System.out.println("------------------working --------------" + add);
		return add;
	}

	@Transactional
	@Override
	public List<Address> getAllAddress() {
		List<Address> addList = new ArrayList<>();
		addressRepository.findAll().forEach(addList::add);
		return addList;
	}

	@Override
	public Address getAddress(Long id) {
		Address add = addressRepository.findAddressById(id);
		return add;
	}

	@Override
	public List<Address> getAddressByUserId(String token) {

		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).orElseThrow(null);
		try {
			List<Address> user = addressRepository.findAddressByUserId(id);
			if (user != null) {
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Address getAddress(String type, String token) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).orElseThrow(null);
		Address add = addressRepository.findAddressBytext(id, type);
		return add;
	}

	@Transactional
	@Override
	public Users deleteAddress(String token, Long addressId) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).orElseThrow(null);
		List<Address> deleteaddress = getAllAddress();
		Address filteredaddress = deleteaddress.stream().filter(address -> address.getAddress().equals(addressId))
				.findFirst().orElseThrow(null);
		deleteaddress.remove(filteredaddress);
		addressRepository.delete(filteredaddress);
		return userRepo.save(userdetails);
	}

}
