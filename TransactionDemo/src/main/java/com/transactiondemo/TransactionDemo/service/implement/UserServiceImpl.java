package com.transactiondemo.TransactionDemo.service.implement;

import com.transactiondemo.TransactionDemo.dto.request.UserRequest;
import com.transactiondemo.TransactionDemo.dto.response.UserResponse;
import com.transactiondemo.TransactionDemo.entity.User;
import com.transactiondemo.TransactionDemo.repository.UserRepository;
import com.transactiondemo.TransactionDemo.service.TestNewService;
import com.transactiondemo.TransactionDemo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
// Check transaction information: TransactionAspectSupport.currentTransactionInfo()

public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final TestNewService testService;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        userRepository.save(user);
        return null;
    }

    // Propagation = REQUIRED case
    // Mac dinh neu khong khai bao gi ve propagation, gia tri default la REQUIRED
    // Che do mac dinh:
    // ollback chi thuc su duoc kich hoat voi RuntimeException
    // Voi cac Exception khac thi se khong rollback lai
    @Transactional(rollbackFor = Exception.class,
            noRollbackFor = EntityNotFoundException.class)
    @Override
    public void updateUser(Long id, UserRequest request) throws Exception {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
//            user.setUsername(request.getUsername());
//            user.setPassword(request.getPassword());
//            userRepository.save(user);
            testService.updateUserTwo(user);
//            updateUserTwo(user);
//            throw new Exception();
            throw new RuntimeException();
        }
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void updateUserSupport(Long id, UserRequest request) {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            updateUserSupportTwo(user);
            throw new RuntimeException();
        }
    }

    /*
    Propagation.SUPPORTS phai cung mot class thi method updateUserSupportTwo() moi run cung transaction
    voi method updateUserSupport()
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void updateUserSupportTwo(User user) {
        userRepository.save(user);
    }

    @Transactional()
    @Override
    public void updateUserMandatory(Long id, UserRequest request) {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
//            updateUserMandatoryTwo(user);
            testService.updateUserMandatoryTwo(user);
//            throw new RuntimeException();
        }

    }

//    @Transactional(propagation = Propagation.MANDATORY)
//    @Transactional(propagation = Propagation.NEVER)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void updateUserMandatoryTwo(User user) {
        userRepository.save(user);
    }


    /*
    Neu khai bao Propagation.REQUIRES_NEW voi method nay, ham nay duoc goi truc tiep trong class nay
    se khong create ra mot new transaction.
    Chung ta phai tao mot class  moi va tao ham moi ben class do voi Propagation.REQUIRES_NEW thi moi
    tao duoc new transaction
    check class TestNewService
     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void updateUserTwo (User user) {
//        user.setPassword("binhcheck");
//        userRepository.save(user);
//    }


}
