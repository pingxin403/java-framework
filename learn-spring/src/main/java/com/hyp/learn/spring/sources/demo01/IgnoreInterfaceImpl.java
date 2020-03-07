package com.hyp.learn.spring.sources.demo01;

import java.util.List;
import java.util.Set;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.sources.demo01
 * hyp create at 19-12-24
 **/
public class IgnoreInterfaceImpl implements IgnoreInterface {

    private List<String> list;
    private Set<String> set;

    @Override
    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public void setSet(Set<String> set) {
        this.set = set;
    }

    public List<String> getList() {
        return list;
    }

    public Set<String> getSet() {
        return set;
    }
}