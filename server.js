const express = require('express')
const User  = require('./src/models/User')
const bodyParser = require('body-parser');
const db = require('./database')

/// Aplicacao
const app = express()

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));





db.authenticate()
  .then(() => {
    console.log('Connection has been established successfully.');
  })
  .catch(err => {
    console.error('Unable to connect to the database:', err);
  });

// Primeiras rotas para teste

app.post('/register', async (req, res) => {
    console.log(User)
    const user = await User.create(req.body);
    res.send('ola');
  });

app.listen(3001)