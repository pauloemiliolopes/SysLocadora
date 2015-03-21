(function() {
	'use strict';

	angular.module('App').controller('VendasCtrl', VendasCtrl);

	VendasCtrl.$inject = [ '$location', 'LocacaoFactory' ];

	function VendasCtrl($location, LocacaoFactory) {

		var vm = this;
		vm.marcado = false;
		vm.filme = null;
		vm.clients = [];
		vm.filmes = [];
		vm.filmesAlugados = [];
		vm.total = 0;

		vm.buscarCliente = function(nome) {
			return LocacaoFactory.getClientes(nome).then(function(data) {
				vm.clients = data;
				return vm.clients;
			});
		};

		vm.buscarFilme = function(nome) {
			return LocacaoFactory.getFilmes(nome).then(function(data) {
				vm.filmes = data;
				return vm.filmes;
			});
		};

		vm.addFilme = function() {
			var filme = vm.filmes[0];
			vm.filmesAlugados.push(filme);
			vm.total += parseFloat(vm.filmes[0].preco);
			
			vm.alugel = {
				cliente : vm.clients[0],
				filmes : vm.filmesAlugados,
				total : vm.total
			}

		};
		
		vm.fecharAluguel = function() {
			return LocacaoFactory.fecharAluguel(vm.alugel).then(function(data) {
				console.log(data);
				vm.result = data;
				return vm.alugel
			}, function(err) {
				return err;
			});
		};

	}
	;
}());
