angular
	.module('app')
	.factory('AuthenticationService', AuthenticationService);

AuthenticationService.$inject = ['$http', '$cookieStore', '$rootScope'];
function AuthenticationService($http, $cookieStore, $rootScope) {
	var service = {};

	service.login = login;
	service.setCredentials = setCredentials;
	service.clearCredentials = clearCredentials;

	return service;

	function login(email, callback) {
		$http.get('http://localhost:8080/UserManagementSystem/api/users/' + email)
			.success(function (data, response) {
				callback(data, response);
			})
			.error(function (data, response) {
				callback(data, response);
			})
	}

	function setCredentials(email) {
		$rootScope.globals = {
			currentUser: {
				email: email
			}
		};

		$cookieStore.put('globals', $rootScope.globals);
	}

	function clearCredentials() {
		$rootScope.globals = {};
		$cookieStore.remove('globals');
	}
}