
module.exports = (sequelize, DataTypes) => {
    const User = sequelize.define( "User", {
      name: {
        type: DataTypes.STRING,
        allowNull: false
      },
      dni: {
          type: DataTypes.INTEGER,
          primaryKey: true,
          allowNull: false
      },
      state: {
          type: DataTypes.STRING(2),
          allowNull: false
      },
      party: {
          type: DataTypes.STRING
      },
      interestList: {
          type: DataTypes.TEXT
      }
    });

    return User
}