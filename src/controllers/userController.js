const Sequelize = require('sequelize')
const sequelize = Sequelize('postgres://tbdjalmu:Jo4MQ2SEj-R2EbGoXCSOnzlfD83YLG1R@motty.db.elephantsql.com:5432/tbdjalmu')


sequelize
  .authenticate()
  .then(() => {
    console.log('Connection has been established successfully.');
  })
  .catch(err => {
    console.error('Unable to connect to the database:', err);
  });


const __dirname = "../models"
const Project = sequelize.import(__dirname + "/User.js")


