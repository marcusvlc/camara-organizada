
const Sequelize = require('sequelize')
const db = require('../../database')

const User = db.define( "Usuario", {
    name: {
    type: Sequelize.STRING,
    allowNull: false
    },
    dni: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        allowNull: false
    },
    state: {
        type: Sequelize.STRING(2),
        allowNull: false
    },
    party: {
        type: Sequelize.STRING
    },
    interestList: {
        type: Sequelize.TEXT
    }
    
}, {
    freezeTableName: true,
    tableName: 'Usuario',
});

module.exports = User

