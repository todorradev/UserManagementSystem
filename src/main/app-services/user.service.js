angular
	.module('app')
	.factory('UserService', UserService);

UserService.$inject = ['$http', '$q', '$location'];
function UserService($http, $q, $location) {
	var service = {};
	const configUrl = location.protocol + "//" + location.hostname + ":" + 8080 + '/api/users/'; //this will not work for ipv6!

	service.getAll = getAll;
	service.getByEmail = getByEmail;
	service.create = create;
	service.update = update;
	service.deleteUser = deleteUser;
	service.update = update;

	return service;

	function getAll() {
		return $http.get(configUrl).then(handleSuccess, handleError('Error getting all users'));
	}

	function getByEmail(email) {
		return $http.get(configUrl + email).then(handleSuccess, handleError('Error loading the logged in user.'));
	}

	function create(user) {
		return $http.post(configUrl, user).then(handleSuccess, handleError('Error creating user'));
	}

	function update(user) {
		return $http.put(configUrl, user).then(handleSuccess, handleError('Error updating user'));
	}

	function deleteUser(id) {
		return $http.delete(configUrl + id).then(handleSuccess, handleError('Error deleting user'));
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
