Rails.application.routes.draw do

	get '/' => 'animals#index'
	get '/animals/todos' => 'animals#todos'
	get '/animals/encontrei/:id' => 'animals#encontrei'

  resources :animals
  resources :donos
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
