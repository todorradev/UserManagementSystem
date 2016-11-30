angular
	.module('app')
	.controller('HomeController', HomeController);

HomeController.$inject = ['UserService', '$rootScope', 'FlashService'];
function HomeController(UserService, $rootScope, FlashService) {
	var vm = this;

	vm.user = null;
	vm.allUsers = [];
	vm.deleteUser = deleteUser;
	vm.updateUser = updateUser;

	loadCurrentUser();
	loadAllUsers();

	function loadCurrentUser() {
		UserService.getByEmail($rootScope.globals.currentUser.email)
			.then(function (data) {
				if(data.message != null)
					FlashService.Error(data.message);
				else
					vm.user = data;
			});
	}

	function loadAllUsers() {
		UserService.getAll()
			.then(function (data) {
				if(data.message != null)
					FlashService.Error(data.message);
				else
					vm.allUsers = data;
			});
	}

	function deleteUser(id) {
		UserService.deleteUser(id)
			.then(function(data) {
				if(data.message != null)
					FlashService.Error(data.message);
				else
					loadAllUsers();
			});
	}

	function updateUser(user) {
		UserService.update(user)
		.then(function(data) {
			if(data.message != null)
				FlashService.Error(data.message);
			else {
				loadCurrentUser();
				loadAllUsers();
			}
		});
	}
}