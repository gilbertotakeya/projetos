require 'test_helper'

class DonosControllerTest < ActionDispatch::IntegrationTest
  setup do
    @dono = donos(:one)
  end

  test "should get index" do
    get donos_url
    assert_response :success
  end

  test "should get new" do
    get new_dono_url
    assert_response :success
  end

  test "should create dono" do
    assert_difference('Dono.count') do
      post donos_url, params: { dono: { Email: @dono.Email, Nome: @dono.Nome, Senha: @dono.Senha, Telefone: @dono.Telefone } }
    end

    assert_redirected_to dono_url(Dono.last)
  end

  test "should show dono" do
    get dono_url(@dono)
    assert_response :success
  end

  test "should get edit" do
    get edit_dono_url(@dono)
    assert_response :success
  end

  test "should update dono" do
    patch dono_url(@dono), params: { dono: { Email: @dono.Email, Nome: @dono.Nome, Senha: @dono.Senha, Telefone: @dono.Telefone } }
    assert_redirected_to dono_url(@dono)
  end

  test "should destroy dono" do
    assert_difference('Dono.count', -1) do
      delete dono_url(@dono)
    end

    assert_redirected_to donos_url
  end
end
