(ns demo.p2
  (:require
   [re-frame.core :as re-frame]))

;; events.cljs
(re-frame/reg-event-db
 :update
 (fn [db [_ new-text]]
   (assoc db :text new-text)))

;; subs.cljs
(re-frame/reg-sub
 :text
 (fn [db _] (:text db)))

;; views.cljs
(defn echoing-input [label]
  [:<>
   [:label label
    [:input
     {:type      :text
      :value     @(re-frame/subscribe [:text])
      :on-change #(re-frame/dispatch [:update (-> % .-target .-value)])}]]
   [:p @(re-frame/subscribe [:text])]])

(defn form []
  [:form
   [echoing-input "Input One"]])

(defn main []
  [form])
