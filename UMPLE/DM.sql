//%% NEW FILE Stand BEGINS HERE %%

-- PLEASE DO NOT EDIT THIS CODE
-- This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!



CREATE TABLE IF NOT EXISTS `stand`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*stand Associations*/
  expo_expo_name VARCHAR(255),

  /*stand Attributes*/
  stand_name VARCHAR(255),
  PRIMARY KEY(stand_name)

);


ALTER TABLE `stand` ADD CONSTRAINT `fk_stand_expo_expo_name` FOREIGN KEY (`expo_expo_name`) REFERENCES `expo`(`expo_name`);




//%% NEW FILE Vote BEGINS HERE %%

-- PLEASE DO NOT EDIT THIS CODE
-- This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!



CREATE TABLE IF NOT EXISTS `vote`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*vote Associations*/
  user_username VARCHAR(255),
  stand_stand_name VARCHAR(255),

  /*vote Attributes*/
  score INT,
  PRIMARY KEY(score)

);


ALTER TABLE `vote` ADD CONSTRAINT `fk_vote_user_username` FOREIGN KEY (`user_username`) REFERENCES `user`(`username`);
ALTER TABLE `vote` ADD CONSTRAINT `fk_vote_stand_stand_name` FOREIGN KEY (`stand_stand_name`) REFERENCES `stand`(`stand_name`);




//%% NEW FILE Expo BEGINS HERE %%

-- PLEASE DO NOT EDIT THIS CODE
-- This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!



CREATE TABLE IF NOT EXISTS `expo`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*expo Attributes*/
  expo_name VARCHAR(255),
  start_time DATE,
  end_time DATE,
  PRIMARY KEY(expo_name)

);






//%% NEW FILE User BEGINS HERE %%

-- PLEASE DO NOT EDIT THIS CODE
-- This code was generated using the UMPLE 1.29.1.4450.6749b7105 modeling language!



CREATE TABLE IF NOT EXISTS `user`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*user Attributes*/
  username VARCHAR(255),
  pin INT,
  is_admin BLOB,
  is_verified BLOB,
  is_jury BLOB,
  PRIMARY KEY(username)

);