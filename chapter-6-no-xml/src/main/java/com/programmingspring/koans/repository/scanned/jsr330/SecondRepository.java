package com.programmingspring.koans.repository.scanned.jsr330;

import com.programmingspring.koans.repository.MyRepository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class SecondRepository implements MyRepository {
}
