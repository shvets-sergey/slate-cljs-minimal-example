(ns slate-example.app
  (:require ["react" :as react]
            ["react-dom" :as react-dom]
            ["slate" :refer (createEditor)]
            ["slate-react" :refer (Slate Editable withReact)]))


(defn Editor
  []
  (let [editor (react/useMemo #(-> (createEditor)
                                   (withReact)) #js []) ;; bind this?
        init-val (clj->js [{"type" "paragraph"
                            "children" [{"text" "Test line"}]}])
        [value set-value] (react/useState init-val)
        editable-comp (react/createElement Editable #js {} nil)
        slate-comp (react/createElement Slate
                                        #js {"editor" editor
                                             "value" value
                                             "onChange" #(set-value %)}
                                        editable-comp)]
    slate-comp))


(defn ^:dev/after-load entry-point!
  []
  (let [rootEl (js/document.getElementById "root")
        ]
    (react-dom/render (react/createElement Editor #js {} nil) rootEl)))