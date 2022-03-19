INSERT INTO `user` (`location_id`, `username`, `password`) VALUES ('1', 'bill', 'password');
INSERT INTO `user` (`location_id`, `username`, `password`) VALUES ('12', 'user2', 'password1');

INSERT INTO `forecast_provider` (`provider_id`) VALUES ('1');
INSERT INTO `forecast_provider` (`provider_id`) VALUES ('2');
INSERT INTO `forecast_provider` (`provider_id`) VALUES ('3');
INSERT INTO `forecast_provider` (`provider_id`) VALUES ('4');
INSERT INTO `forecast_provider` (`provider_id`) VALUES ('5');



INSERT INTO `user_forecast_provider` (`user_id`, `forecast_provider_id`) VALUES ('1', '1');
INSERT INTO `user_forecast_provider` (`user_id`, `forecast_provider_id`) VALUES ('2', '1');