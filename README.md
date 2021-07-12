## Usage





### Activity (ViewBinding)

```kotlin
class MainActivity : UniverseViewActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvSample.text = "HI"
    }
}
```



### Fragment (ViewBinding)

```kotlin
class SampleViewFragment : UniverseViewFragment<FragmentMainBinding>(
  R.layout.fragment_main,
  FragmentMainBinding::bind) {
    override fun onViewCreated(bind: FragmentMainBinding, savedInstanceState: Bundle?) {

    }
}
```



### RecyclerView (ViewBinding)

```kotlin
class SampleAdapter : UniverseRecyclerViewAdapter<UniverseItemModel>() {

  override fun getItemViewType(position: Int): Int = when(list[position]) {
    is SomeModel -> R.layout.item_some
    else -> R.layout.item_other
  }

  override fun holder(viewType: Int, view: View): UniverseViewHolder<UniverseItemModel> {
    return when(viewType) {
      R.layout.item_some -> SampleViewHolder(ItemSomeBinding.bind(view))
      else -> OtherHolder(ItemOtherBinding.bind(view))
    }
  }
}

class SampleViewHolder(binding: ItemSomeBinding) : UniverseViewHolder<UniverseItemModel>(binding)

data class SomeModel(
  val title: String
) : UniverseItemModel
```

