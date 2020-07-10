package com.estate.controller.admin.api;

import com.estate.builder.UserBuilder;
import com.estate.dto.UserDTO;
import com.estate.exception.MyException;
import com.estate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/user")
public class UserAPI{

        @Autowired
        private IUserService userService;

        @PostMapping
        public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
                return ResponseEntity.ok(userService.insert(userDTO));

        }

        @PutMapping("/{id}")
        public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") long id) {
                return ResponseEntity.ok(userService.update(userDTO, id));

        }

        @DeleteMapping
        public ResponseEntity<String> updateUser(@RequestBody long[] ids) {
            if (ids.length > 0) {
                userService.deleteUser(ids);
            }
            return ResponseEntity.ok("success");
        }

        @GetMapping("/usages")
        public ResponseEntity<UserDTO> checkExist(@RequestBody UserDTO userDTO) {
            try {
                userService.checkUserNameOrEmailExist(userDTO.getUserName(),userDTO.getEmail(),userDTO.getId());
            } catch (MyException e) {
                userDTO.setMessageException(e.getMessage());
                e.printStackTrace();
            }
            return ResponseEntity.ok(userDTO);
        }

        @GetMapping
        public ResponseEntity<List<UserDTO>> loadUser(@RequestParam(value = "role", required = false) String role,
                                                      @RequestParam(value = "buildingid", required = false) Long buildingId,
                                                      @RequestParam(value = "customerid", required = false) Long customerid) {
            UserBuilder builder = new UserBuilder.Builder()
                    .setRole(role)
                    .setBuildingId(buildingId)
                    .setCustomerId(customerid)
                    .build();
            return ResponseEntity.ok(userService.getUsers(builder));
        }
}
