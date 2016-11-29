(function () {
	'use strict';

	angular
		.module('app')
		.factory('UserService', UserService);

	UserService.$inject = ['$http'];
	function UserService($http) {
		var service = {};

		service.getAll = getAll;
		service.getByEmail = getByEmail;
		service.create = create;
		service.update = update;
		service.deleteUser = deleteUser;
		service.update = update;

		return service;

		function getAll() {
			return $http.get('http://localhost:8080/UserManagementSystem/api/users').then(handleSuccess, handleError('Error getting all users'));
		}

		function getByEmail(email) {
			return $http.get('http://localhost:8080/UserManagementSystem/api/users/' + email).then(handleSuccess, handleError('Error getting user by email'));
		}

		function create(user) {
			return $http.post('http://localhost:8080/UserManagementSystem/api/users', user).then(handleSuccess, handleError('Error creating user'));
		}

		function update(user) {
			return $http.put('http://localhost:8080/UserManagementSystem/api/users', user).then(handleSuccess, handleError('Error updating user'));
		}

		function deleteUser(id) {
			return $http.delete('http://localhost:8080/UserManagementSystem/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
		}

		function handleSuccess(res) {
			return res.data;
		}

		function handleError(error) {
			return function () {
				return { success: false, message: error };
			};
		}
	}
})();
