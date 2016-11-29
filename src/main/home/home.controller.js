﻿(function () {
	'use strict';

	angular
		.module('app')
		.controller('HomeController', HomeController);

	HomeController.$inject = ['UserService', '$rootScope'];
	function HomeController(UserService, $rootScope) {
		var vm = this;

		vm.user = null;
		vm.allUsers = [];
		vm.deleteUser = deleteUser;
		vm.updateUser = updateUser;

		(function initController() {
			loadCurrentUser();
			loadAllUsers();
		})();

		function loadCurrentUser() {
			UserService.getByEmail($rootScope.globals.currentUser.email)
				.then(function (user) {
					vm.user = user;
				});
		}

		function loadAllUsers() {
			UserService.getAll()
				.then(function (users) {
					vm.allUsers = users;
				});
		}

		function deleteUser(id) {
			UserService.deleteUser(id);
		}

		function updateUser(user) {
			UserService.update(user);
		}
	}
})();