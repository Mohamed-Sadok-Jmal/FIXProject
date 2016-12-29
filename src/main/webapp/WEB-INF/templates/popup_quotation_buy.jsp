<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="boxes" class="row">
        <div id="dialog" class="window">
            <span class="text-info">Passer un ordre</span>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form ng-form-commit class="form-control-static" name="newOrderForm" ng-submit="newOrderForm.$valid && addNewOrder()" novalidate>
                        <div class="row form-group">
                            <label for="inputValue" class="control-label col-sm-12">Valeur</label>
                            <select class="col-sm-4 form-control" id="inputValue" name="inputValue"
                                    ng-model="order.id_quotation"
                                    ng-options="quotation.id as quotation.name for quotation in quotations"
                                    required>
                        	</select>
                        	<div class="col-sm-4">{{quotationToBuy.nbShares}} actions</div>
                        </div>
                        <div class="row form-group">
                            <label for="inputClient" class="control-label col-sm-12">Client</label>
                            <select class="col-sm-4 form-control" id="inputClient" name="inputClient"
                                    ng-model="order.id_client"
                                    ng-options="client.id as client.name for client in clients"
                                    required>
                        	</select>
                        </div>
                        <div class="row form-group">
                            <label for="inputQte" class="control-label col-sm-12">Quantité</label>
                            <input type="number" min="1" max="{{quotationToBuy.nbShares}}" class="col-sm-4 form-control" id="inputQte" name="inputQte" ng-model="order.qte" ng-change="checkQte()" required />
                        	<div class="col-sm-4" ng-show="order.type != 'ATP'">{{order.price*order.qte}} dinar(s)</div>
                        </div>
                        <div class="row form-group">
                            <label for="inputType" class="col-sm-12 control-label">Type</label>
                            <select class="col-sm-12 form-control" id="inputType" name="inputType"
                                    ng-model="order.type"
                                    ng-options="o.v as o.n for o in [{ n: 'Au marché', v: 'ATP' }, { n: 'Limité', v: 'Limit' }]"
                                    ng-selected="order.type">
                        	</select>
                        </div>
                        <div class="row form-group" ng-if="order.type != 'ATP'">
                            <label for="inputPrice" class="col-sm-12 control-label">Prix</label>
                        	<input type="number" class="col-sm-12 form-control" id="inputPrice" name="inputPrice" ng-model="order.price" ng-change="checkType()" required />
                        </div>
                        <input type="submit" class="btn btn-info" value="OK" ng-disabled="!newOrderForm.$valid" />
                        <button class="btn btn-info close-popup" ng-click="clearForm()">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
        <div ng-click="clearForm()" style="width: 1478px; font-size: 32pt; color:white; height: 602px; display: none; opacity: 0.8;" id="mask"></div>
    </div>