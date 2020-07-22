package com.zhs.repositroy.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/14 11:25
 * @package: com.zhs.repositroy.secondary
 * @description:
 */
public interface MessageRepositroy extends JpaRepository<Message,Long> {
}
