package com.programmingspring.koans.repository.scanned.spring;

import com.programmingspring.koans.repository.MyRepository;
import org.springframework.stereotype.Repository;

@Repository("firstRepository")
public class FirstRepo implements MyRepository {
}
