INSERT INTO `app_role` (`role_name`) VALUES ('ROLE_ADMIN');
INSERT INTO `app_role` (`role_name`) VALUES ('ROLE_STAFF');
INSERT INTO `app_role` (`role_name`) VALUES ('ROLE_USER');

INSERT INTO `app_user` (`password`, `user_name`, `creation_date`) VALUES ('$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'admin', '2022-08-08');
INSERT INTO `app_user` (`password`, `user_name`, `creation_date`) VALUES ('$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'staff', '2022-08-09');
INSERT INTO `app_user` (`password`, `user_name`, `creation_date`) VALUES ('$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 'user', '2022-08-09');

INSERT INTO `user_role` (`role_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `user_role` (`role_id`, `user_id`) VALUES ('2', '1');
INSERT INTO `user_role` (`role_id`, `user_id`) VALUES ('3', '1');
INSERT INTO `user_role` (`role_id`, `user_id`) VALUES ('2', '2');
INSERT INTO `user_role` (`role_id`, `user_id`) VALUES ('3', '2');
INSERT INTO `user_role` (`role_id`, `user_id`) VALUES ('3', '3');