(ns app.ant-design.components
  (:refer-clojure :exclude [comment empty list])
  (:require [reagent.core  :as r]
            [clojure.string :as str]
            [goog.object :as gobj]
            ["@ant-design/icons" :as icons]
            ["antd" :as antd]))

(defn- get-path
  [component-name]
  (str/split component-name #"\."))

(defn component
  [component-name]
  (let [path (get-path component-name)]
    (r/adapt-react-class
     (apply gobj/getValueByKeys antd path))))

(def affix (component "Affix"))
(def alert (component "Alert"))
(def anchor (component "Anchor"))
(def anchor-link (component "Anchor.Link"))
(def auto-complete (component "AutoComplete"))
(def auto-complete-opt-group (component "AutoComplete.OptGroup"))
(def auto-complete-option (component "AutoComplete.Option"))
(def avatar (component "Avatar"))
(def back-top (component "BackTop"))
(def badge (component "Badge"))
(def breadcrumb (component "Breadcrumb"))
(def breadcrumb-item (component "Breadcrumb.Item"))
(def breadcrumb-separator (component "Breadcrumb.Separator"))
(def button (component "Button"))
(def button-group (component "Button.Group"))
(def calendar (component "Calendar"))
(def card (component "Card"))
(def card-grid (component "Card.Grid"))
(def card-meta (component "Card.Meta"))
(def carousel (component "Carousel"))
(def cascader (component "Cascader"))
(def checkbox (component "Checkbox"))
(def checkbox-group (component "Checkbox.Group"))
(def col (component "Col"))
(def collapse (component "Collapse"))
(def collapse-panel (component "Collapse.Panel"))
(def comment (component "Comment"))
(def config-provider (component "ConfigProvider"))
(def date-picker (component "DatePicker"))
(def date-picker-month-picker (component "DatePicker.MonthPicker"))
(def date-picker-range-picker (component "DatePicker.RangePicker"))
(def date-picker-week-picker (component "DatePicker.WeekPicker"))
(def descriptions (component "Descriptions"))
(def descriptions-item (component "Descriptions.Item"))
(def divider (component "Divider"))
(def drawer (component "Drawer"))
(def dropdown (component "Dropdown"))
(def dropdown-button (component "Dropdown.Button"))
(def empty (component "Empty"))
(def form (component "Form"))
(def form-item (component "Form.Item"))
(def icon (component "Icon"))
(def input (component "Input"))
(def input-group (component "Input.Group"))
(def input-password (component "Input.Password"))
(def input-search (component "Input.Search"))
(def input-text-area (component "Input.TextArea"))
(def input-number (component "InputNumber"))
(def layout (component "Layout"))
(def layout-content (component "Layout.Content"))
(def layout-footer (component "Layout.Footer"))
(def layout-header (component "Layout.Header"))
(def layout-sider (component "Layout.Sider"))
(def list (component "List"))
(def list-item (component "List.Item"))
(def list-item-meta (component "List.Item.Meta"))
(def mentions (component "Mentions"))
(def mentions-option (component "Mentions.Option"))
(def menu (component "Menu"))
(def menu-divider (component "Menu.Divider"))
(def menu-item (component "Menu.Item"))
(def menu-item-group (component "Menu.ItemGroup"))
(def menu-sub-menu (component "Menu.SubMenu"))
(def modal (component "Modal"))
(def page-header (component "PageHeader"))
(def pagination (component "Pagination"))
(def popconfirm (component "Popconfirm"))
(def popover (component "Popover"))
(def progress (component "Progress"))
(def radio (component "Radio"))
(def radio-button (component "Radio.Button"))
(def radio-group (component "Radio.Group"))
(def rate (component "Rate"))
(def result (component "Result"))
(def row (component "Row"))
(def select (component "Select"))
(def select-opt-group (component "Select.OptGroup"))
(def select-option (component "Select.Option"))
(def skeleton (component "Skeleton"))
(def slider (component "Slider"))
(def spin (component "Spin"))
(def statistic (component "Statistic"))
(def statistic-countdown (component "Statistic.Countdown"))
(def steps (component "Steps"))
(def steps-step (component "Steps.Step"))
(def switch (component "Switch"))
(def table (component "Table"))
(def table-column (component "Table.Column"))
(def table-column-group (component "Table.ColumnGroup"))
(def tabs (component "Tabs"))
(def tabs-tab-pane (component "Tabs.TabPane"))
(def tag (component "Tag"))
(def tag-checkable-tag (component "Tag.CheckableTag"))
(def time-picker (component "TimePicker"))
(def timeline (component "Timeline"))
(def timeline-item (component "Timeline.Item"))
(def tooltip (component "Tooltip"))
(def transfer (component "Transfer"))
(def transfer-list (component "Transfer.List"))
(def transfer-operation (component "Transfer.Operation"))
(def transfer-search (component "Transfer.Search"))
(def tree (component "Tree"))
(def tree-directory-tree (component "Tree.DirectoryTree"))
(def tree-tree-node (component "Tree.TreeNode"))
(def tree-select (component "TreeSelect"))
(def tree-select-tree-node (component "TreeSelect.TreeNode"))
(def typography (component "Typography"))
(def typography-paragraph (component "Typography.Paragraph"))
(def typography-text (component "Typography.Text"))
(def typography-title (component "Typography.Title"))
(def upload (component "Upload"))
(def upload-dragger (component "Upload.Dragger"))
