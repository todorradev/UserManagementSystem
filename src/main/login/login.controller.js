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
		AuthenticationService.login(vm.email, function (data, response) {
			if(response == 200) {
				AuthenticationService.setCredentials(vm.email);
				$location.path('/');
				return;
			}
			FlashService.Error("Email doesn't exist");
		});
	};
}
