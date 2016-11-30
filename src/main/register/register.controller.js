angular
	.module('app')
	.controller('RegisterController', RegisterController);

RegisterController.$inject = ['UserService', '$location', 'FlashService'];
function RegisterController(UserService, $location, FlashService) {
	var vm = this;

	vm.register = register;

	function register() {
		vm.dataLoading = true;
		UserService.create(vm.user)
			.then(function (data) {
				if(data.message != null) {
					FlashService.Error("This email is already registered!");
					vm.dataLoading = false;
				}
				else {
					FlashService.Success('Registration successful', true);
					$location.path('/login');
				}
			});
	}
}
