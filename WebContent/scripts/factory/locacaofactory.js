(function() {
	'use strict';

	angular.module('App').factory('LocacaoFactory', LocacaoFactory);

	LocacaoFactory.$inject = [ '$http' ];

	function LocacaoFactory($http) {
		var urlFilmes = '/SistemaLocadora/api/filmes?nome=';
		var urlClientes = '/SistemaLocadora/api/clientes?nome=';
		var urlLocacao = '/SistemaLocadora/api/fechar';

		var service = {
			getFilmes : getFilmes,
			getClientes : getClientes,
			fecharAluguel : fecharAluguel
		}

		return service;

		function getFilmes(nome) {
			return $http.get(urlFilmes + nome).then(function(data) {
				return data.data;
			}, function(err) {
				return err;
			});
		}

		function getClientes(nome) {
			return $http.get(urlClientes + nome).then(function(data) {
				return data.data;
			}, function(err) {
				return err;
			});
		}

		function fecharAluguel(saida) {
			return $http.post(urlLocacao, saida, {
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).then(function(data) {
				return data.data;
			}, function(err) {
				return err;
			});
		}
	}
})();