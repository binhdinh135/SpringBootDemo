package com.example.pessimisticlocking.foo;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FooService {

    private final FooRepository fooRepository;

    @Transactional
    public Foo save(int id, String text) {
        Foo foo = fooRepository.findAndLockById(id).orElseThrow();
        if (StringUtils.isBlank(foo.getText())) {
            foo.setText(text);
        } else {
            foo.setText(foo.getText() + " " + text);
        }
        return fooRepository.saveAndFlush(foo);
    }

}
