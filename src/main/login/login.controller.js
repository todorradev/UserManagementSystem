angular
	.module('app')
	.controller('LoginController', LoginController);

LoginController.$inject = ['$location','AuthenticationService', 'FlashService'];
function LoginController($location, AuthenticationService, FlashService) {
	var vm = this;

	vm.login = login;

	(function initController() {
		// reset login status
		AuthenticationService.clearCredentials();
	})();

	function login() {
		AuthenticationService.login(vm.email, function (data) {
			if(data != "") {
				AuthenticationService.setCredentials(vm.email);
				$location.path('/');
				return;
			}
			var response = {};
			response.message = "Email doesn't exist";
			FlashService.Error(response.message);
		});
	};
}
