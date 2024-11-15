DATABASE "qualityControl" entities, TABLE "tasks":
  title              VARCHAR(45)
  description        VARCHAR(255)
  TaskType           VARCHAR(45)
  completed          BOOLEAN
  min_point          INT
  max_point          INT
  current_point      INT
  priority           INT
  deadline          TIMESTAMP
  creation_date     TIMESTAMP  

NOTE run this before create creation_date column:
  run this ALTER TABLE `tasks` 
  ADD COLUMN `creation_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `priority`;



  cd .\qualitycontrol\ => ./mvnw clean install
